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

case class AnmeldeDaten(person_id: Int,
                        strasse: String,
                        hausnummer: String,
                        stiege: String,
                        tuer: String,
                        plz: String,
                        ort: String,
                        bundesland: String,
                        zuzugAusAusland: String,
                        hauptwohnsitz: String,
                        unterkunftgeber: String)

case class HauptwohnsitzDaten(person_id: Int,
                              strasse: String,
                              hausnummer: String,
                              stiege: String,
                              tuer: String,
                              plz: String,
                              ort: String,
                              bundesland: String)

case class AbmeldeDaten(person_id: Int,
                        strasse: String,
                        hausnummer: String,
                        stiege: String,
                        tuer: String,
                        plz: String,
                        ort: String,
                        bundesland: String,
                        zuzugAusAusland: String)

case class FremdeDaten(person_id: Int,
                       art: String,
                       nummer: String,
                       datum: String,
                       behoerde:String,
                       staat:String)