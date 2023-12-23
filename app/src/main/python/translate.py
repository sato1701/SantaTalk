import numpy as np
import librosa
import itertools

# 各STFT時間のパワーについて最大値の周波数をとる添え字をとり、その増減率を返す
def calc_diff_rate(audio_data, sr):
    # 1. 音素のスペクトルを作成
    # メルスペクトログラムにしてみる
    mel_spectrogram = librosa.feature.melspectrogram(y=audio_data, sr=sr)

    # 2. スペクトルを2000hz以下の部分に区切る
    mel_freqs = librosa.mel_frequencies(fmax=sr)
    mel_spectrogram_cut = mel_spectrogram[mel_freqs <= 2000, :]

    # 3. 各STFT時間のパワーについて最大値の周波数をとる
    max_freq_ids = np.argmax(mel_spectrogram_cut, axis=0)

    # 2点間の増減をみる
    diff_flat_count = 0 # 点間の値が等しい
    diff_increase_count = 0 # 点間の値が上昇している
    diff_decrease_count = 0 # 点間の値が下降している
    for i in range(1, len(max_freq_ids)):
        if max_freq_ids[i - 1] == max_freq_ids[i]:
            diff_flat_count += 1
        elif max_freq_ids[i - 1] < max_freq_ids[i]:
            diff_increase_count += 1
        elif max_freq_ids[i - 1] > max_freq_ids[i]:
            diff_decrease_count += 1

    # print(diff_flat_count, diff_increase_count, diff_decrease_count)
    total = diff_flat_count + diff_increase_count + diff_decrease_count
    # print(diff_flat_count / total, diff_increase_count / total, diff_decrease_count / total)

    return (diff_flat_count / total, diff_increase_count / total, diff_decrease_count / total)


# 分類器
def predict(audio_length, flat_rate, increase_rate, decrease_rate):
    # resultは、ホ：1、ホー:2、ホ↗:3、ホ↘:4となる
    result = 0

    if audio_length <= 0.2:# 音声が0.2秒以下
        result = 1
        return result

    if flat_rate >= 0.7:
        result = 2
        return result

    if increase_rate > decrease_rate:
        result = 3
        return result
    elif increase_rate < decrease_rate:
        result = 4
        return result
    else:
        result = 2
        return result


def term_recognition(audio_data, sr):
    audio_length = librosa.get_duration(y=audio_data, sr=sr)
    flat_rate, increase_rate, decrease_rate = calc_diff_rate(audio_data, sr)
    result = predict(audio_length, flat_rate, increase_rate, decrease_rate)
    return result


def change_to_santa(idx):
    if idx == 1:
        return "ホ"
    if idx == 2:
        return "ホー"
    if idx == 3:
        return "ホ⤴"
    if idx == 4:
        return "ホ⤵"


def word_dispatch(file_path):
    SAMPLING_RATE = 44100

    audio_data, sr = librosa.load(file_path, sr=SAMPLING_RATE)
    # フレームのズラす幅の指定
    hop_length = 512

    # フレームで切り出す回数
    nms = ((audio_data.shape[0])//hop_length)+1

    # 最初と最後をフレームで切り出せるようにゼロパディング
    audio_data_bf = np.zeros(hop_length*2)
    audio_data_af = np.zeros(hop_length*2)
    audio_data_concat = np.concatenate([audio_data_bf, audio_data, audio_data_af])


    zero_cross_list = []
    for j in range(nms):
        zero_cross = 0
        # フレームによる切り出し
        audio_data_this = audio_data_concat[j*512:j*512+2048]
        for i in range(audio_data_this.shape[0]-1):
            # もし正負が変わったらという条件
            if (np.sign(audio_data_this[i]) - np.sign(audio_data_this[i+1])) != 0:
                zero_cross += 1
        zero_cross_list.append(zero_cross)

    # 最大値が1になるように正規化
    zero_cross_list = np.array(zero_cross_list)/max(zero_cross_list)

    # 閾値で分ける
    zero_cross_list = (zero_cross_list < 0.05)

    spec = librosa.stft(audio_data, hop_length=512, n_fft=2048)
    logmel_db = librosa.feature.melspectrogram(sr=sr, S=librosa.power_to_db(np.abs(spec)**2), n_mels=80)

    phe_array = []
    word_array = []
    tf = [(k, len(list(g))) for k, g in itertools.groupby(zero_cross_list)]
    index = 0
    for i in range(len(tf)):
        if tf[i][0] and (tf[i][1] < 50):
            phe_array.append(audio_data[index:index+tf[i-1][1]*512])
        elif not(tf[i][0]) and (tf[i][1] >= 50):
            phe_array.append(audio_data[index:index+tf[i-1][1]*512])
            word_array.append(phe_array)
            phe_array = []

        index += tf[i][1] * 512

    word_predict = ""
    for word in word_array:
        phe_predict = ""
        for term in word:
            result = term_recognition(term, sr)
            phe_predict += change_to_santa(result)
        word_predict += (" " + phe_predict)
    word_predict = word_predict[1:]

    return word_predict
