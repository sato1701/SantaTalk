package com.kosenhacku2023.santatalk;

import java.util.HashMap;
import java.util.Map;

class Dictionary {
    String[] JapanWordNoun = { "私", "あなた", "彼(彼ら)", "これ(あれ)", "クリスマス", "サンタ", "プレゼント", "煙突", "暖炉", "家", "チキン", "ケーキ",
            "ツリー", "こども", "爆発", "トナカイ", "ソリ", "ミルク", "クッキー", "食べ物", "動物", "靴下", "雪", "幸せ(夢, 希望)", "もの(こと)", "恋人",
            "サーフィン", "人", "飲み物", "気持ち", "お菓子", "鼻", "クリスマスカード", "絵", "手紙", "おもちゃ" };
    String[] JapanWordIntVerb = { "降る", "ある", "寝る", "笑う", "喜ぶ" };
    String[] JapanWordTranVerb = { "行く", "見る", "食べる", "与える", "だ", "する", "置く", "作る", "かく", "話す", "感謝する" };
    String[] JapanWordAdjective = { "いい(好き)", "よくない(好きではない)", "あたたかい", "つめたい", "赤い" };
    String[] JapanWordNumeral = { "少し", "たくさん" };
    String[] JapanWordInterjection = { "メリークリスマス!", "ありがとう", "どういたしまして", "さようなら" };
    String[] JapanWordAuxVerb = { "だろう(したい)", "だった" };
    String[] SantaWordNoun = { "ホ⤴ホ⤴", "ホ⤴ホ―", "ホホホ⤴", "ホ―ホ⤴ホ⤴", "ホ―ホ⤴", "ホ―ホ⤵", "ホ⤴ホ", "ホホ―ホ⤵", "ホホ⤴ホ―", "ホホ⤴ホ⤴", "ホホ⤴ホ⤵", "ホホ⤴",
            "ホホ―", "ホ―ホホ", "ホ―ホホ―", "ホ―ホホ⤴", "ホ―ホホ⤵", "ホ―ホ―ホ", "ホ―ホ―ホ―", "ホ―ホ⤴ホ⤵", "ホ―ホ⤵ホ⤴", "ホ⤴ホホ", "ホ⤴ホホ―", "ホ⤴ホホ⤴", "ホ⤴ホホ⤵", "ホ⤴ホ―ホ",
            "ホ⤴ホ―ホ―", "ホ⤴ホ―ホ⤴", "ホ⤴ホ―ホ⤵", "ホ⤴ホ⤴ホ", "ホ⤴ホ⤴ホ―", "ホ⤴ホ⤴ホ⤵", "ホ⤴ホ⤵ホ―", "ホ⤴ホ⤵ホ⤴", "ホ⤵ホホ", "ホ⤴ホ⤵" };
    String[] SantaWordIntVerb = { "ホ⤵ホホ⤴", "ホ⤵ホホ⤵", "ホ⤵ホ―ホ", "ホ⤵ホ―ホ―", "ホホ" };
    String[] SantaWordTranVerb = { "ホ⤵ホ―ホ⤵", "ホ⤵ホ⤴ホ", "ホ⤵ホ⤴ホ⤴", "ホ⤵ホ⤴ホ⤵", "ホ⤵ホ⤵ホ―", "ホ⤵ホ⤵ホ⤴", "ホ⤵ホ―ホ⤴", "ホホ⤵ホ⤴", "ホホ⤵ホ―", "ホホ―ホ", "ホ―ホ" };
    String[] SantaWordAdjective = { "ホ―ホ―", "ホホ⤵", "ホホ―ホ―", "ホホ―ホ⤴", "ホホホ―" };
    String[] SantaWordNumeral = { "ホホホ", "ホ⤵ホホ―" };
    String[] SantaWordInterjection = { "ホホホ⤵", "ホ⤵ホ-", "ホ⤵ホ⤴", "ホ―ホ⤴ホ" };
    String[] SantaWordAuxVerb = { "ホ―ホ―ホ⤴", "ホ―ホ―ホ⤵" };
    String[] JapanWordIntVerbFuture = {"降るだろう(したい)", "あるだろう(したい)", "寝るだろう(したい)", "笑うだろう(したい)", "喜ぶだろう(したい)"};
    String[] JapanWordTranVerbFuture = { "行くだろう(したい)", "見るだろう(したい)", "食べるだろう(したい)", "与えるだろう(したい)", "だろう", "するだろう(したい)",
            "置くだろう(したい)", "作るだろう(したい)", "かくだろう(したい)", "話すだろう(したい)", "感謝するだろう(したい)" };
    String[] JapanWordIntVerbPast = { "降った", "あった", "寝た", "笑った", "喜んだ" };
    String[] JapanWordTranVerbPast = { "行った", "見た", "食べた", "与えた", "だった", "した", "置いた", "作った", "かいた", "話した", "感謝した" };

    public Map<String, String> JapanToSanta = new HashMap<String, String>() {
        {
            for (int i = 0; i < JapanWordNoun.length; i++) {
                put(JapanWordNoun[i], SantaWordNoun[i]);
            }
            for (int i = 0; i < JapanWordIntVerb.length; i++) {
                put(JapanWordIntVerb[i], SantaWordIntVerb[i]);
            }
            for (int i = 0; i < JapanWordTranVerb.length; i++) {
                put(JapanWordTranVerb[i], SantaWordTranVerb[i]);
            }
            for (int i = 0; i < JapanWordAdjective.length; i++) {
                put(JapanWordAdjective[i], SantaWordAdjective[i]);
            }
            for (int i = 0; i < JapanWordNumeral.length; i++) {
                put(JapanWordNumeral[i], SantaWordNumeral[i]);
            }
            for (int i = 0; i < JapanWordInterjection.length; i++) {
                put(JapanWordInterjection[i], SantaWordInterjection[i]);
            }
            for (int i = 0; i < JapanWordAuxVerb.length; i++) {
                put(JapanWordAuxVerb[i], SantaWordAuxVerb[i]);
            }
        }
    };
    public Map<String, String> SantaToJapan = new HashMap<String, String>() {
        {
            for (int i = 0; i < JapanWordNoun.length; i++) {
                put(SantaWordNoun[i], JapanWordNoun[i]);
            }
            for (int i = 0; i < JapanWordIntVerb.length; i++) {
                put(SantaWordIntVerb[i], JapanWordIntVerb[i]);
            }
            for (int i = 0; i < JapanWordTranVerb.length; i++) {
                put(SantaWordTranVerb[i], JapanWordTranVerb[i]);
            }
            for (int i = 0; i < JapanWordAdjective.length; i++) {
                put(SantaWordAdjective[i], JapanWordAdjective[i]);
            }
            for (int i = 0; i < JapanWordNumeral.length; i++) {
                put(SantaWordNumeral[i], JapanWordNumeral[i]);
            }
            for (int i = 0; i < JapanWordInterjection.length; i++) {
                put(SantaWordInterjection[i], JapanWordInterjection[i]);
            }
            for (int i = 0; i < JapanWordAuxVerb.length; i++) {
                put(SantaWordAuxVerb[i], JapanWordAuxVerb[i]);
            }
        }
    };
    Map<String, String> NounJapanToSanta = new HashMap<String, String>() {
        {
            for (int i = 0; i < JapanWordNoun.length; i++) {
                put(JapanWordNoun[i], SantaWordNoun[i]);
            }
            for (int i = 0; i < JapanWordNoun.length; i++) {
                put(JapanWordNoun[i], SantaWordNoun[i]);
            }
        }
    };
    Map<String, String> NounSantaToJapan = new HashMap<String, String>() {
        {
            for (int i = 0; i < JapanWordNoun.length; i++) {
                put(SantaWordNoun[i], JapanWordNoun[i]);
            }
            for (int i = 0; i < JapanWordNoun.length; i++) {
                put(SantaWordNoun[i], JapanWordNoun[i]);
            }
        }
    };
    Map<String, String> IntVerbJapanToSanta = new HashMap<String, String>() {
        {
            for (int i = 0; i < JapanWordIntVerb.length; i++) {
                put(JapanWordIntVerb[i], SantaWordIntVerb[i]);
            }
            for (int i = 0; i < JapanWordIntVerb.length; i++) {
                put(JapanWordIntVerb[i], SantaWordIntVerb[i]);
            }
        }
    };
    Map<String, String> IntVerbSantaToJapan = new HashMap<String, String>() {
        {
            for (int i = 0; i < JapanWordIntVerb.length; i++) {
                put(SantaWordIntVerb[i], JapanWordIntVerb[i]);
            }
            for (int i = 0; i < JapanWordIntVerb.length; i++) {
                put(SantaWordIntVerb[i], JapanWordIntVerb[i]);
            }
        }
    };
    Map<String, String> AdjectiveJapanToSanta = new HashMap<String, String>() {
        {
            for (int i = 0; i < JapanWordAdjective.length; i++) {
                put(JapanWordAdjective[i], SantaWordAdjective[i]);
            }
            for (int i = 0; i < JapanWordAdjective.length; i++) {
                put(JapanWordAdjective[i], SantaWordAdjective[i]);
            }
        }
    };
    Map<String, String> AdjectiveSantaToJapan = new HashMap<String, String>() {
        {
            for (int i = 0; i < JapanWordAdjective.length; i++) {
                put(SantaWordAdjective[i], JapanWordAdjective[i]);
            }
            for (int i = 0; i < JapanWordAdjective.length; i++) {
                put(SantaWordAdjective[i], JapanWordAdjective[i]);
            }
        }
    };
    Map<String, String> NumeralJapanToSanta = new HashMap<String, String>() {
        {
            for (int i = 0; i < JapanWordNumeral.length; i++) {
                put(JapanWordNumeral[i], SantaWordNumeral[i]);
            }
            for (int i = 0; i < JapanWordNumeral.length; i++) {
                put(JapanWordNumeral[i], SantaWordNumeral[i]);
            }
        }
    };
    Map<String, String> NumeralSantaToJapan = new HashMap<String, String>() {
        {
            for (int i = 0; i < JapanWordNumeral.length; i++) {
                put(SantaWordNumeral[i], JapanWordNumeral[i]);
            }
            for (int i = 0; i < JapanWordNumeral.length; i++) {
                put(SantaWordNumeral[i], JapanWordNumeral[i]);
            }
        }
    };
    Map<String, String> InterjectionJapanToSanta = new HashMap<String, String>() {
        {
            for (int i = 0; i < JapanWordInterjection.length; i++) {
                put(JapanWordInterjection[i], SantaWordInterjection[i]);
            }
            for (int i = 0; i < JapanWordInterjection.length; i++) {
                put(JapanWordInterjection[i], SantaWordInterjection[i]);
            }
        }
    };
    Map<String, String> InterjectionSantaToJapan = new HashMap<String, String>() {
        {
            for (int i = 0; i < JapanWordInterjection.length; i++) {
                put(SantaWordInterjection[i], JapanWordInterjection[i]);
            }
            for (int i = 0; i < JapanWordInterjection.length; i++) {
                put(SantaWordInterjection[i], JapanWordInterjection[i]);
            }
        }
    };
    Map<String, String> AuxVerbJapanToSanta = new HashMap<String, String>() {
        {
            for (int i = 0; i < JapanWordAuxVerb.length; i++) {
                put(JapanWordAuxVerb[i], SantaWordAuxVerb[i]);
            }
            for (int i = 0; i < JapanWordAuxVerb.length; i++) {
                put(JapanWordAuxVerb[i], SantaWordAuxVerb[i]);
            }
        }
    };
    Map<String, String> AuxVerbSantaToJapan = new HashMap<String, String>() {
        {
            for (int i = 0; i < JapanWordAuxVerb.length; i++) {
                put(SantaWordAuxVerb[i], JapanWordAuxVerb[i]);
            }
            for (int i = 0; i < JapanWordAuxVerb.length; i++) {
                put(SantaWordAuxVerb[i], JapanWordAuxVerb[i]);
            }
        }
    };
    Map<String, String> TranVerbJapanToSanta = new HashMap<String, String>() {
        {
            for (int i = 0; i < JapanWordTranVerb.length; i++) {
                put(JapanWordTranVerb[i], SantaWordTranVerb[i]);
            }
            for (int i = 0; i < JapanWordTranVerb.length; i++) {
                put(JapanWordTranVerb[i], SantaWordTranVerb[i]);
            }
        }
    };
    Map<String, String> TranVerbSantaToJapan = new HashMap<String, String>() {
        {
            for (int i = 0; i < JapanWordTranVerb.length; i++) {
                put(SantaWordTranVerb[i], JapanWordTranVerb[i]);
            }
            for (int i = 0; i < JapanWordTranVerb.length; i++) {
                put(SantaWordTranVerb[i], JapanWordTranVerb[i]);
            }
        }
    };
    Map<String, String> VerbFutureJapanToSanta = new HashMap<String, String>() {
        {
            for (int i = 0; i < JapanWordIntVerb.length; i++) {
                put(JapanWordIntVerbFuture[i], SantaWordIntVerb[i]);
            }
            for (int i = 0; i < JapanWordTranVerb.length; i++) {
                put(JapanWordTranVerbFuture[i], SantaWordTranVerb[i]);
            }
        }
    };
    Map<String, String> VerbFutureSantaToJapan = new HashMap<String, String>() {
        {
            for (int i = 0; i < JapanWordIntVerb.length; i++) {
                put(SantaWordIntVerb[i], JapanWordIntVerbFuture[i]);
            }
            for (int i = 0; i < JapanWordTranVerb.length; i++) {
                put(SantaWordTranVerb[i], JapanWordTranVerbFuture[i]);
            }
        }
    };
    Map<String, String> VerbPastJapanToSanta = new HashMap<String, String>() {
        {
            for (int i = 0; i < JapanWordIntVerb.length; i++) {
                put(JapanWordIntVerbPast[i], SantaWordIntVerb[i]);
            }
            for (int i = 0; i < JapanWordTranVerb.length; i++) {
                put(JapanWordTranVerbPast[i], SantaWordTranVerb[i]);
            }
        }
    };
    Map<String, String> VerbPastSantaToJapan = new HashMap<String, String>() {
        {
            for (int i = 0; i < JapanWordIntVerb.length; i++) {
                put(SantaWordIntVerb[i], JapanWordIntVerbPast[i]);
            }
            for (int i = 0; i < JapanWordTranVerb.length; i++) {
                put(SantaWordTranVerb[i], JapanWordTranVerbPast[i]);
            }
        }
    };
    Map<String, String> IntVerbFutureJapanToSanta = new HashMap<String, String>() {
        {
            for (int i = 0; i < JapanWordIntVerb.length; i++) {
                put(JapanWordIntVerbFuture[i], SantaWordIntVerb[i]);
            }
        }
    };
    Map<String, String> IntVerbFutureSantaToJapan = new HashMap<String, String>() {
        {
            for (int i = 0; i < JapanWordIntVerb.length; i++) {
                put(SantaWordIntVerb[i], JapanWordIntVerbFuture[i]);
            }
        }
    };
    Map<String, String> IntVerbPastJapanToSanta = new HashMap<String, String>() {
        {
            for (int i = 0; i < JapanWordIntVerb.length; i++) {
                put(JapanWordIntVerbPast[i], SantaWordIntVerb[i]);
            }
        }
    };
    Map<String, String> IntVerbPastSantaToJapan = new HashMap<String, String>() {
        {
            for (int i = 0; i < JapanWordIntVerb.length; i++) {
                put(SantaWordIntVerb[i], JapanWordIntVerbPast[i]);
            }
        }
    };
    Map<String, String> TranVerbFutureJapanToSanta = new HashMap<String, String>() {
        {
            for (int i = 0; i < JapanWordTranVerb.length; i++) {
                put(JapanWordTranVerbFuture[i], SantaWordTranVerb[i]);
            }
        }
    };
    Map<String, String> TranVerbFutureSantaToJapan = new HashMap<String, String>() {
        {
            for (int i = 0; i < JapanWordTranVerb.length; i++) {
                put(SantaWordTranVerb[i], JapanWordTranVerbFuture[i]);
            }
        }
    };
    Map<String, String> TranVerbPastJapanToSanta = new HashMap<String, String>() {
        {
            for (int i = 0; i < JapanWordTranVerb.length; i++) {
                put(JapanWordTranVerbPast[i], SantaWordTranVerb[i]);
            }
        }
    };
    Map<String, String> TranVerbPastSantaToJapan = new HashMap<String, String>() {
        {
            for (int i = 0; i < JapanWordTranVerb.length; i++) {
                put(SantaWordTranVerb[i], JapanWordTranVerbPast[i]);
            }
        }
    };



//	DEBUG
//	public void printDic(Map<String, String> map) {
//		for (Map.Entry<String, String> entrySet : map.entrySet()) {
//			System.out.println(entrySet.getKey() + " = " + entrySet.getValue());
//		}
//	}
}
