import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.redundent.kotlin.xml.xml
import java.io.File
import javax.swing.filechooser.FileSystemView

@Serializable
data class JS(val surname:String, val name:String, val secondName:String, val age:Int)
fun main(){
    var choice=true
    while (choice) {
        println("1.Вывод информации о дисках")
        println("2.Работа с файлом")
        println("3.Работа с JSON")
        println("4.Работа с XML")
        println("5.Прервать выполнение программы")
            when(readLine().toString()){
                "1"->{
                    val kok = FileSystemView.getFileSystemView()
                    val lol = File.listRoots()
                for (disk in lol) {
                    println("Название:$disk")
                    println("Тип: ${kok.getSystemTypeDescription(disk)}")
                    println("Объём диска:${disk.totalSpace}")
                    println("Свободное пространство:${disk.freeSpace}")
                    println("Метка:$disk")
                }
            }
                "2"->{
                    val file = File("C:\\Для лабы", "laba1_1.txt")
                    file.createNewFile()
                    println("Введите строку")
                    val str = readLine().toString()
                    file.writeText(str)
                    println(file.readText())
                    println("Хотите удалить файл? 1.Да 2.Нет")
                    if(readLine().toString()=="1")
                    file.delete()
                }
                "3"->{
                    println("Введите свою фамилию")
                    val sur = readLine().toString()
                    println("Введите своё имя")
                    val name = readLine().toString()
                    println("Введите своё отчество")
                    val second = readLine().toString()
                    println("Введите свой возраст")
                    val age = readLine().toString().toInt()
                    val json = Json.encodeToString<JS>(JS(sur, name, second, age))
                    val fileJs = File("C:\\Для лабы", "laba1_2.txt")
                    fileJs.writeText(json)
                    println(fileJs.readText())
                    println("Хотите удалить файл? 1.Да 2.Нет")
                    if(readLine().toString()=="1")
                    fileJs.delete()
                }

                "4"->{
                    val people = xml("people") {
                        xmlns = "http://example.com/people"
                        "person" {
                            attribute("id", 1)
                            "firstName" {
                                -"John"
                            }
                            "lastName" {
                                -"Doe"
                            }
                            "phone" {
                                -"555-555-5555"
                            }
                        }
                    }

                    val asString = people.toString()
                    val filexml = File("C:\\Для лабы", "labXml.txt")
                    filexml.writeText(asString)
                    println(filexml.readText())
                    println("Хотите удалить файл? 1.Да 2.Нет")
                    if(readLine().toString()=="1")
                    filexml.delete()
                }
                "5"->{
                    print("До новых встреч!")
                    choice=false
                }

                else->println("Подумай над своим поведением и выбери нормально!")
            }
    }
}