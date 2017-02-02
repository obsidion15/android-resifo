package at.fh.swengb.android_resifo

/**
  * Created by Sabine on 02.02.2017.
  */
class Anmeldung {
  private var anmeldung_id: Int = 0
  private var p_id: Int = 0
  private var strasse: String = ""
  private var hausnr: String = ""
  private var stiege: String = ""
  private var tuer: String = ""
  private var plz: String = ""
  private var ort: String = ""
  private var bundesland: String = ""
  private var zuzugAusAusland: String = ""
  private var hauptwohnsitz: String = ""
  private var unterkunftgeber: String = ""

  def getAnmeldungId = anmeldung_id
  def getPersonId = p_id
  def getStrasse = strasse
  def getHausnr = hausnr
  def getStiege = stiege
  def getTuer = tuer
  def getPlz= plz
  def getOrt= ort
  def getBundesland = bundesland
  def getZuzugAusAusland = zuzugAusAusland
  def getHauptwohnsitz = hauptwohnsitz
  def getUnterkunftgeber = unterkunftgeber

  def setAnmeldungId(n: Int) = anmeldung_id = n
  def setPersonId(n: Int) = p_id = n
  def setStrasse(n: String) = strasse = n
  def setHausnr(n: String) = hausnr = n
  def setStiege(n: String) = stiege = n
  def setTuer(n: String) = tuer = n
  def setPlz(n: String) = plz = n
  def setOrt(n: String) = ort = n
  def setBundesland(n: String) = bundesland = n
  def setZuzugAusAusland(n: String) = zuzugAusAusland = n
  def setHauptwohnsitz(n: String) = hauptwohnsitz = n
  def setUnterkunftgeber(n: String) = unterkunftgeber = n

}
