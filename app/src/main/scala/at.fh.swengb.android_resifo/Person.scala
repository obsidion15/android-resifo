package at.fh.swengb.android_resifo

/**
  * Created by Sabine on 02.02.2017.
  */
class Person {
  private var person_id: Int = 0
  private var nachname: String = ""
  private var vorname: String = ""
  private var nachnameAlt: String = ""
  private var geburtsdatum: String = ""
  private var geburtsort: String = ""
  private var geschlecht: String = ""
  private var religion: String = ""
  private var familienstand: String = ""
  private var staatsangehoerigkeit: String = ""

  def getPersonId = person_id
  def getNachname = nachname
  def getVorname = vorname
  def getNachnameAlt = nachnameAlt
  def getGeburtsdatum = geburtsdatum
  def getGeburtsort = geburtsort
  def getGeschlecht = geschlecht
  def getReligion = religion
  def getFamilienstand = familienstand
  def getStaatsangehoerigkeit = staatsangehoerigkeit

  def setPersonId(n: Int) = person_id = n
  def setNachname(n: String) = nachname = n
  def setVorname(n: String) = vorname = n
  def setNachnameAlt(n: String) = nachnameAlt= n
  def setGeburtsdatum(n: String) = geburtsdatum = n
  def setGeburtsort(n: String) = geburtsort = n
  def setGeschlecht(n: String) = geschlecht = n
  def setReligion(n: String) = religion = n
  def setFamilienstand(n: String) = familienstand = n
  def setStaatsangehoerigkeit(n: String) = staatsangehoerigkeit = n

}
