package com.kosenhacku2023.santatalk;

import java.util.ArrayList;
import java.util.List;

public class Model {
    Dictionary dic;
    public Model(Controller controller) {
        dic = new Dictionary();
    }

    void init() {
        //TODO
    }

    void requestPermissions( ){
        //TODO
    }

    void recordStart( ){
        //TODO
    }

    void recordStop( ){
        //TODO
    }

    static String[] dispatchToWords(String str) {
        int wordIndex = 0;
        int pointer = 0;
        String[] text = new String[5];
        for(int i = 0; i < str.length(); i++) {
            if(str.charAt(i) == ' ') {
                if(wordIndex == 5) {
                    throw new RuntimeException("Too many words in the sentence.");
                }
                text[wordIndex++] = str.substring(pointer, i);
                pointer = i+1;
            }
        }
        text[wordIndex] = str.substring(pointer);
        return text;
    }

    static String connectToSentence(List<String> textList) {
        StringBuilder sentence = new StringBuilder();
        for(int i = 0; i < textList.size()-1; i++) {
            sentence.append(textList.get(i)).append(' ');
        }
        sentence.append(textList.get(textList.size()-1));
        return sentence.toString();
    }

    List<String> translateSJtoSS(String[] str){
        List<String> returnString = new ArrayList<>();
        for(String word : str){
            if(word == null) break;
            returnString.add(dic.JapanToSanta.get(word));
        }

        //Grammar check
        int index = 0;
        boolean isBadGrammar = false;

        if(dic.NounJapanToSanta.get(str[index]) != null){
            index++;
            if(dic.AuxVerbJapanToSanta.get(str[index])!= null) {
                index++;
            }

            if(dic.IntVerbJapanToSanta.get(str[index])!= null) {
                index++;
            }else if(dic.TranVerbSantaToJapan.get(str[index])!= null) {
                index++;
            }else if(dic.AdjectiveJapanToSanta.get(str[index])!= null) {
                index++;
            }else{
                isBadGrammar = true;
            }
            if(dic.NumeralJapanToSanta.get(str[index])!= null) {
                index++;
            }
        }else if(dic.InterjectionJapanToSanta.get(str[index])!= null){
            index++;
        }else{
            isBadGrammar = true;
        }

        if(index != returnString.size()) isBadGrammar = true;
        if(isBadGrammar) returnString.add("(文法エラー)");

        return returnString;
    }
//    List<String> translateSJtoSS(String[] str) { //Santa(Japan) to Santa(Santa)
//        int index = 0;
//        List<String> returnString = new ArrayList<>();
//        String buffer;
//        int flagFuture = 0, flagPast = 0, flagGrammar = 0;
//        String Numeral;
//
//        if (index >= str.length) {
//            return returnString;
//        }
//        if ((buffer = dic.NounJapanToSanta.get(str[index])) != null) {
//            returnString.add(buffer);
//            index++;
//        } else if ((buffer = dic.InterjectionJapanToSanta.get(str[index])) != null) {
//            returnString.add(buffer);
//            flagGrammar = 1;
//            if (flagGrammar != 1)
//                returnString.add("(文法エラー)");
//            return returnString;
//        } else {
//            System.out.println("Noun_or_Interjection_error:" + str[index]);
//            if (flagGrammar != 1)
//                returnString.add("(文法エラー)");
//            return returnString;
//        }
//        if (index >= str.length) {
//            if (flagGrammar != 1)
//                returnString.add("(文法エラー)");
//            return returnString;
//        }
//        if ((buffer = dic.AuxVerbJapanToSanta.get(str[index])) != null) {
//            if (str[index].equals("だろう(したい)")) {
//                flagFuture = 1;
//            } else if (str[index].equals("だった")) {
//                flagPast = 1;
//            }
//            returnString.add(buffer);
//            index++;
//        }
//        if (index >= str.length) {
//            if (flagGrammar != 1)
//                returnString.add("(文法エラー)");
//            return returnString;
//        }
//        if ((buffer = dic.IntVerbJapanToSanta.get(str[index])) != null) {
//            returnString.add(buffer);
//            index++;
//            flagGrammar = 1;
//        } else if ((buffer = dic.TranVerbJapanToSanta.get(str[index])) != null) {
//            returnString.add(buffer);
//            index++;
//            if (index >= str.length) {
//                if (flagGrammar != 1)
//                    returnString.add("(文法エラー)");
//                return returnString;
//            }
//            if ((buffer = dic.NounJapanToSanta.get(str[index])) != null) {
//                returnString.add(buffer);
//                index++;
//                flagGrammar = 1;
//            } else {
//                System.out.println("Tran_Noun_error:" + str[index]);
//                if (flagGrammar != 1)
//                    returnString.add("(文法エラー)");
//                return returnString;
//            }
//        } else if ((buffer = dic.AdjectiveJapanToSanta.get(str[index])) != null) {
//            returnString.add(buffer);
//            index++;
//            flagGrammar = 1;
//        } else {
//            System.out.println("IntVerb_or_tranVerb_or_Adjective_error:" + str[index]);
//            if (flagGrammar != 1)
//                returnString.add("(文法エラー)");
//            return returnString;
//        }
//        if (index >= str.length) {
//            if (flagGrammar != 1)
//                returnString.add("(文法エラー)");
//            return returnString;
//        }
//        if ((buffer = dic.NumeralJapanToSanta.get(str[index])) != null) {
//            returnString.add(buffer);
//            index++;
//            flagGrammar = 1;
//        }
//        if (flagGrammar != 1)
//            returnString.add("(文法エラー)");
//        return returnString;
//    }
    List<String> translateSStoNJ(String[] str){ // Santa(Santa) to Natural(Japan)
        int index = 0;
        List<String> returnString = new ArrayList<>();
        String buffer;
        int flagFuture = 0, flagPast = 0, flagGrammar = 0;
        String Numeral;

        if (index >= str.length) {
            return returnString;
        }
        if ((buffer = dic.NounSantaToJapan.get(str[index])) != null) {
            returnString.add(buffer);
            index++;
        } else if ((buffer = dic.InterjectionSantaToJapan.get(str[index])) != null) {
            returnString.add(buffer);
            flagGrammar = 1;
            if (flagGrammar != 1)
                returnString.add("(文法エラー)");
            return returnString;
        } else {
            System.out.println("Noun_or_Interjection_error:" + str[index]);
            if (flagGrammar != 1)
                returnString.add("(文法エラー)");
            return returnString;
        }
        if (index >= str.length) {
            if (flagGrammar != 1)
                returnString.add("(文法エラー)");
            return returnString;
        }
        returnString.add("は");
        returnString.add("Numeral"); // 数詞の場所を確保

        if ((buffer = dic.AuxVerbSantaToJapan.get(str[index])) != null) {
            if (buffer.equals("だろう(したい)")) {
                flagFuture = 1;
            } else if (buffer.equals("だった")) {
                flagPast = 1;
            }
            index++;
        } else {
        }
        if (index >= str.length) {
            if (flagGrammar == 1)
                returnString.set(returnString.indexOf("Numeral"), "");
            else
                returnString.add("(文法エラー)");
            return returnString;
        }
        if ((buffer = dic.IntVerbSantaToJapan.get(str[index])) != null) {
            if (flagFuture == 1) {
                returnString.add(dic.VerbFutureSantaToJapan.get(str[index]));
            } else if (flagPast == 1) {
                returnString.add(dic.VerbPastSantaToJapan.get(str[index]));
            } else {
                returnString.add(buffer);
            }
            index++;
            flagGrammar = 1;
        } else if ((buffer = dic.TranVerbSantaToJapan.get(str[index])) != null) {
            returnString.add("Noun");
            if (buffer.equals("行く") || buffer.equals("与える") || buffer.equals("感謝する")) {
                returnString.add("に");
            } else if (buffer.equals("見る") || buffer.equals("食べる") || buffer.equals("置く") || buffer.equals("作る")
                    || buffer.equals("かく")) {
                returnString.add("を");
            } else if (buffer.equals("話す")) {
                returnString.add("話す");
            } else {
            }
            if (flagFuture == 1) {
                returnString.add(dic.TranVerbFutureSantaToJapan.get(str[index]));
            } else if (flagPast == 1) {
                returnString.add(dic.TranVerbPastSantaToJapan.get(str[index]));
            } else {
                returnString.add(buffer);
            }
            index++;
            if (index >= str.length) {
                if (flagGrammar == 1)
                    returnString.set(returnString.indexOf("Numeral"), "");
                else
                    returnString.add("(文法エラー)");
                return returnString;
            }
            if ((buffer = dic.NounSantaToJapan.get(str[index])) != null) {
                returnString.set(returnString.indexOf("Noun"), buffer);
                index++;
                flagGrammar = 1;
            } else {
                System.out.println("Tran_Noun_error:" + str[index]);
                returnString.set(returnString.indexOf("Noun"), "");
                if (flagGrammar == 1)
                    returnString.set(returnString.indexOf("Numeral"), "");
                else
                    returnString.add("(文法エラー)");
                return returnString;
            }
        } else if ((buffer = dic.AdjectiveSantaToJapan.get(str[index])) != null) {
            returnString.add(buffer);
            index++;
            flagGrammar = 1;
        } else {
            System.out.println("IntVerb_or_tranVerb_or_Adjective_error:" + str[index]);
            if (flagGrammar == 1)
                returnString.set(returnString.indexOf("Numeral"), "");
            else
                returnString.add("(文法エラー)");
            return returnString;
        }
        if (index >= str.length) {
            if (flagGrammar == 1)
                returnString.set(returnString.indexOf("Numeral"), "");
            else
                returnString.add("(文法エラー)");
            return returnString;
        }
        if ((buffer = dic.NumeralSantaToJapan.get(str[index])) != null) {
            returnString.set(returnString.indexOf("Numeral"), buffer);
            index++;
        } else {
            returnString.set(returnString.indexOf("Numeral"), "");
        }
        if (flagGrammar != 1)
            returnString.add("(文法エラー)");
        return returnString;
    }
    List<String> translateNJtoSJ(String[] str) { // Natural(Japan) to Santa(Japan)
        int index = 0;
        List<String> returnString = new ArrayList<String>();
        String buffer;
        int flagFuture = 0, flagPast = 0, flagGrammar = 0;
        String Numeral;

        if (dic.NounJapanToSanta.get(str[index]) != null) {
            returnString.add(str[index]);
            index++;
            returnString.add("AuxVerb"); // 助動詞確保
        } else if (dic.InterjectionJapanToSanta.get(str[index]) != null) {
            returnString.add(str[index]);
            index++;
            flagGrammar = 1;
            if (flagGrammar != 1)
                returnString.add("(文法エラー)");
            return returnString;
        } else {
            System.out.println("Noun_or_Interjection_error:" + str[index]);
            if (flagGrammar != 1)
                returnString.add("(文法エラー)");
            return returnString;
        }
        if (index >= str.length) {
            if (flagGrammar == 1) {
                if (flagFuture == 1) {
                    returnString.set(returnString.indexOf("AuxVerb"), "だろう(したい)");
                } else if (flagPast == 1) {
                    returnString.set(returnString.indexOf("AuxVerb"), "だった");
                } else {
                    returnString.set(returnString.indexOf("AuxVerb"), "");
                }
            } else {
                return null;
            }
            return returnString;
        }
        if (str[index].equals("は")) {
            index++;
        } else {
            System.out.println("は_error:" + str[index]);
            if (flagGrammar == 1) {
                if (flagFuture == 1) {
                    returnString.set(returnString.indexOf("AuxVerb"), "だろう(したい)");
                } else if (flagPast == 1) {
                    returnString.set(returnString.indexOf("AuxVerb"), "だった");
                } else {
                    returnString.set(returnString.indexOf("AuxVerb"), "");
                }
            } else {

            }
            return returnString;
        }
        if (index >= str.length) {
            if (flagGrammar == 1) {
                if (flagFuture == 1) {
                    returnString.set(returnString.indexOf("AuxVerb"), "だろう(したい)");
                } else if (flagPast == 1) {
                    returnString.set(returnString.indexOf("AuxVerb"), "だった");
                } else {
                    returnString.set(returnString.indexOf("AuxVerb"), "");
                }
            } else {

            }
            return returnString;
        }
        if (dic.NumeralJapanToSanta.get(str[index]) != null) {
            Numeral = str[index];
            index++;
        } else {
            Numeral = "";
        }
        if (index >= str.length) {
            if (flagGrammar == 1) {
                if (flagFuture == 1) {
                    returnString.set(returnString.indexOf("AuxVerb"), "だろう(したい)");
                } else if (flagPast == 1) {
                    returnString.set(returnString.indexOf("AuxVerb"), "だった");
                } else {
                    returnString.set(returnString.indexOf("AuxVerb"), "");
                }
                returnString.add(Numeral);
            } else {
                returnString.add("(文法エラー)");
            }
            return returnString;
        }
        if ((buffer = dic.IntVerbJapanToSanta.get(str[index])) != null) {
            returnString.add(str[index]);
            index++;
            flagGrammar = 1;
        } else if ((buffer = dic.IntVerbFutureJapanToSanta.get(str[index])) != null) {
            returnString.add(dic.IntVerbSantaToJapan.get(buffer));
            index++;
            flagGrammar = 1;
            flagFuture = 1;
        } else if ((buffer = dic.IntVerbPastJapanToSanta.get(str[index])) != null) {
            returnString.add(dic.IntVerbSantaToJapan.get(buffer));
            index++;
            flagGrammar = 1;
            flagPast = 1;
        } else if (dic.NounJapanToSanta.get(str[index]) != null) {
            returnString.add("TranVerb");
            returnString.add(str[index]);
            index++;
            if (index >= str.length) {
                if (flagGrammar == 1) {
                    if (flagFuture == 1) {
                        returnString.set(returnString.indexOf("AuxVerb"), "だろう(したい)");
                    } else if (flagPast == 1) {
                        returnString.set(returnString.indexOf("AuxVerb"), "だった");
                    } else {
                        returnString.set(returnString.indexOf("AuxVerb"), "");
                    }
                    returnString.add(Numeral);
                } else {
                    returnString.add("(文法エラー)");
                }
                return returnString;
            }
            if (str[index].equals("に") || str[index].equals("を") || str[index].equals("と")) {
                index++;
            } else if (str[index].equals("だ") || str[index].equals("だろう") || str[index].equals("だった")
                    || str[index].equals("する")
                    || str[index].equals("するだろう(したい)") || str[index].equals("した")) {
            } else {
                System.out.println("Particle_error:" + str[index]);
                if (flagGrammar == 1) {
                    if (flagFuture == 1) {
                        returnString.set(returnString.indexOf("AuxVerb"), "だろう(したい)");
                    } else if (flagPast == 1) {
                        returnString.set(returnString.indexOf("AuxVerb"), "だった");
                    } else {
                        returnString.set(returnString.indexOf("AuxVerb"), "");
                    }
                    returnString.add(Numeral);
                } else {
                    returnString.add("(文法エラー)");
                }
                return returnString;
            }
            if (index >= str.length) {
                if (flagGrammar == 1) {
                    if (flagFuture == 1) {
                        returnString.set(returnString.indexOf("AuxVerb"), "だろう(したい)");
                    } else if (flagPast == 1) {
                        returnString.set(returnString.indexOf("AuxVerb"), "だった");
                    } else {
                        returnString.set(returnString.indexOf("AuxVerb"), "");
                    }
                    returnString.add(Numeral);
                } else {
                    returnString.add("(文法エラー)");
                }
                return returnString;
            }
            if ((buffer = dic.TranVerbJapanToSanta.get(str[index])) != null) {
                returnString.set(returnString.indexOf("TranVerb"), dic.TranVerbSantaToJapan.get(buffer));
                index++;
                flagGrammar = 1;
            } else if ((buffer = dic.TranVerbFutureJapanToSanta.get(str[index])) != null) {
                returnString.set(returnString.indexOf("TranVerb"), dic.TranVerbSantaToJapan.get(buffer));
                index++;
                flagGrammar = 1;
                flagFuture = 1;
            } else if ((buffer = dic.TranVerbPastJapanToSanta.get(str[index])) != null) {
                returnString.set(returnString.indexOf("TranVerb"), dic.TranVerbSantaToJapan.get(buffer));
                index++;
                flagGrammar = 1;
                flagPast = 1;
            } else {
                System.out.println("Tran_error:" + str[index]);
                if (flagGrammar == 1) {
                    if (flagFuture == 1) {
                        returnString.set(returnString.indexOf("AuxVerb"), "だろう(したい)");
                    } else if (flagPast == 1) {
                        returnString.set(returnString.indexOf("AuxVerb"), "だった");
                    } else {
                        returnString.set(returnString.indexOf("AuxVerb"), "");
                    }
                    returnString.add(Numeral);
                } else {
                    returnString.add("(文法エラー)");
                }
                return returnString;
            }
        } else if (dic.AdjectiveJapanToSanta.get(str[index]) != null) {
            returnString.add(str[index]);
            index++;
            flagGrammar = 1;
        } else {
            System.out.println("IntVerb_or_Noun_or_Adjective_error:" + str[index]);
            if (flagGrammar == 1) {
                if (flagFuture == 1) {
                    returnString.set(returnString.indexOf("AuxVerb"), "だろう(したい)");
                } else if (flagPast == 1) {
                    returnString.set(returnString.indexOf("AuxVerb"), "だった");
                } else {
                    returnString.set(returnString.indexOf("AuxVerb"), "");
                }
                returnString.add(Numeral);
            } else {
                returnString.add("(文法エラー)");
            }
            return returnString;
        }
        if (flagFuture == 1) {
            returnString.set(returnString.indexOf("AuxVerb"), "だろう(したい)");
        } else if (flagPast == 1) {
            returnString.set(returnString.indexOf("AuxVerb"), "だった");
        } else {
            returnString.set(returnString.indexOf("AuxVerb"), "");
        }
        returnString.add(Numeral);
        if (flagGrammar != 1)
            returnString.add("(文法エラー)");
        return returnString;
    }
}