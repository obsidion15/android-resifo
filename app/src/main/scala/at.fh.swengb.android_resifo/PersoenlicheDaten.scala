package at.fh.swengb.android_resifo

/**
  * Created by Martin on 31.01.2017.
  */
case class PersoenlicheDaten(vorname: String,
                             nachname: String,
                             nachnameVorher:String,
                             gebDatum: String,
                             gebOrt: String,
                             geschlecht: String,
                             religion: String,
                             famStand:String,
                             staat:String)

case class AnmeldeDaten(strasse: String,
                        hausnummer: String,
                        stiege: String,
                        tuer: String,
                        plz: String,
                        ort: String,
                        bundesland: String,
                        zuzugAusAusland: String,
                        hauptwohnsitz: String,
                        unterkunftgeber: String)

case class HauptwohnsitzDaten(strasse: String,
                              hausnummer: String,
                              stiege: String,
                              tuer: String,
                              plz: String,
                              ort: String,
                              bundesland: String)

case class AbmeldeDaten(strasse: String,
                        hausnummer: String,
                        stiege: String,
                        tuer: String,
                        plz: String,
                        ort: String,
                        bundesland: String,
                        zuzugAusAusland: String)