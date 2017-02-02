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

  def getPersonId(): Int = person_id
  def getNachname(): String = nachname
  def getVorname(): String  = vorname
  def getNachnameAlt(): String  = nachnameAlt
  def getGeburtsdatum(): String  = geburtsdatum
  def getGeburtsort(): String  = geburtsort
  def getGeschlecht(): String  = geschlecht
  def getReligion(): String  = religion
  def getFamilienstand(): String  = familienstand
  def getStaatsangehoerigkeit(): String  = staatsangehoerigkeit

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
