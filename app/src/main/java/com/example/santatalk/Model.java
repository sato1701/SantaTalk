package com.example.santatalk;

import java.util.ArrayList;
import java.util.List;

public class Model {
    Mode mode;
    Dictionary dic;
    public Model(Controller controller, Mode mode) {

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

    String translate(String InputText){
        //TODO
        String OutPutText = "";

        OutPutText += InputText + "translate";
        // ここに翻訳処理記述お願いします。
        // 返り値はStringでおねがいします。

        return OutPutText;
    }

    List<String> TranslateSJtoSS(String[] str) { //Santa(Japan) to Santa(Santa)
        int index = 0;
        List<String> returnString = new ArrayList<String>();
        String buffer;
        int flagFuture = 0, flagPast = 0, flagGrammar = 0;
        String Numeral;

        if (index >= str.length) {
            return returnString;
        }
        if ((buffer = dic.NounJapanToSanta.get(str[index])) != null) {
            returnString.add(buffer);
            index++;
        } else if ((buffer = dic.InterjectionJapanToSanta.get(str[index])) != null) {
            returnString.add(buffer);
            flagGrammar = 1;
            if (flagGrammar == 1) {
                return returnString;
            } else {
                return null;
            }
        } else {
            System.out.println("Noun_or_Interjection_error:" + str[index]);
            if (flagGrammar == 1) {
                return returnString;
            } else {
                return null;
            }
        }
        if (index >= str.length) {
            if (flagGrammar == 1) {
                return returnString;
            } else {
                return null;
            }
        }
        if ((buffer = dic.AuxVerbJapanToSanta.get(str[index])) != null) {
            if (str[index].equals("だろう(したい)")) {
                flagFuture = 1;
            } else if (str[index].equals("だった")) {
                flagPast = 1;
            }
            returnString.add(buffer);
            index++;
        }
        if (index >= str.length) {
            if (flagGrammar == 1) {
                return returnString;
            } else {
                return null;
            }
        }
        if ((buffer = dic.IntVerbJapanToSanta.get(str[index])) != null) {
            returnString.add(buffer);
            index++;
            flagGrammar = 1;
        } else if ((buffer = dic.TranVerbJapanToSanta.get(str[index])) != null) {
            returnString.add(buffer);
            index++;
            if (index >= str.length) {
                if (flagGrammar == 1) {
                    return returnString;
                } else {
                    return null;
                }
            }
            if ((buffer = dic.NounJapanToSanta.get(str[index])) != null) {
                returnString.add(buffer);
                index++;
                flagGrammar = 1;
            } else {
                System.out.println("Tran_Noun_error:" + str[index]);
                if (flagGrammar == 1) {
                    return returnString;
                } else {
                    return null;
                }
            }
        } else if ((buffer = dic.AdjectiveJapanToSanta.get(str[index])) != null) {
            returnString.add(buffer);
            index++;
            flagGrammar = 1;
        } else {
            System.out.println("IntVerb_or_tranVerb_or_Adjective_error:" + str[index]);
            if (flagGrammar == 1) {
                return returnString;
            } else {
                return null;
            }
        }
        if (index >= str.length) {
            if (flagGrammar == 1) {
                return returnString;
            } else {
                return null;
            }
        }
        if ((buffer = dic.NumeralJapanToSanta.get(str[index])) != null) {
            returnString.add(buffer);
            index++;
            flagGrammar = 1;
        }
        if (flagGrammar == 1) {
            return returnString;
        } else {
            return null;
        }
    }
    List<String> TranslateSStoNJ(String[] str){ // Santa(Santa) to Natural(Japan)
        int index = 0;
        List<String> returnString = new ArrayList<String>();
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
            if (flagGrammar == 1) {
                return returnString;
            } else {
                return null;
            }
        } else {
            System.out.println("Noun_or_Interjection_error:" + str[index]);
            if (flagGrammar == 1) {
                return returnString;
            } else {
                return null;
            }
        }
        if (index >= str.length) {
            if (flagGrammar == 1) {
                return returnString;
            } else {
                return null;
            }
        }
        returnString.add("は");
        returnString.add("Numeral"); // 数詞の場所を確保

        if ((buffer = dic.AuxVerbSantaToJapan.get(str[index])) != null) {
            if (buffer == "だろう(したい)") {
                flagFuture = 1;
            } else if (buffer == "だった") {
                flagPast = 1;
            }
            index++;
        } else {
        }
        if (index >= str.length) {
            if (flagGrammar == 1) {
                returnString.set(returnString.indexOf("Numeral"), "");
                return returnString;
            } else {
                return null;
            }
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
                if (flagGrammar == 1) {
                    returnString.set(returnString.indexOf("Numeral"), "");
                    return returnString;
                } else {
                    return null;
                }
            }
            if ((buffer = dic.NounSantaToJapan.get(str[index])) != null) {
                returnString.set(returnString.indexOf("Noun"), buffer);
                index++;
                flagGrammar = 1;
            } else {
                System.out.println("Tran_Noun_error:" + str[index]);
                returnString.set(returnString.indexOf("Noun"), "");
                if (flagGrammar == 1) {
                    returnString.set(returnString.indexOf("Numeral"), "");
                    return returnString;
                } else {
                    return null;
                }
            }
        } else if ((buffer = dic.AdjectiveSantaToJapan.get(str[index])) != null) {
            returnString.add(buffer);
            index++;
            flagGrammar = 1;
        } else {
            System.out.println("IntVerb_or_tranVerb_or_Adjective_error:" + str[index]);
            if (flagGrammar == 1) {
                returnString.set(returnString.indexOf("Numeral"), "");
                return returnString;
            } else {
                return null;
            }
        }
        if (index >= str.length) {
            if (flagGrammar == 1) {
                returnString.set(returnString.indexOf("Numeral"), "");
                return returnString;
            } else {
                return null;
            }
        }
        if ((buffer = dic.NumeralSantaToJapan.get(str[index])) != null) {
            returnString.set(returnString.indexOf("Numeral"), buffer);
            index++;
        } else {
            returnString.set(returnString.indexOf("Numeral"), "");
        }
        if (flagGrammar == 1) {
            return returnString;
        } else {
            return null;
        }
    }
    List<String> TranslateNJtoSJ(String[] str) { // Natural(Japan) to Santa(Japan)
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
            if (flagGrammar == 1) {
                return returnString;
            } else {
                return null;
            }
        } else {
            System.out.println("Noun_or_Interjection_error:" + str[index]);
            if (flagGrammar == 1) {
                return returnString;
            } else {
                return null;
            }
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
                return returnString;
            } else {
                return null;
            }
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
                return returnString;
            } else {
                return null;
            }
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
                return returnString;
            } else {
                return null;
            }
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
                return returnString;
            } else {
                return null;
            }
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
                    return returnString;
                } else {
                    return null;
                }
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
                    return returnString;
                } else {
                    return null;
                }
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
                    return returnString;
                } else {
                    return null;
                }
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
                    return returnString;
                } else {
                    return null;
                }
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
                return returnString;
            } else {
                return null;
            }
        }
        if (flagFuture == 1) {
            returnString.set(returnString.indexOf("AuxVerb"), "だろう(したい)");
        } else if (flagPast == 1) {
            returnString.set(returnString.indexOf("AuxVerb"), "だった");
        } else {
            returnString.set(returnString.indexOf("AuxVerb"), "");
        }
        returnString.add(Numeral);
        if (flagGrammar == 1) {
            return returnString;
        } else {
            return null;
        }
    }
}
