import kotlinx.serialization.Serializable
import java.beans.XMLDecoder
import java.beans.XMLEncoder
import java.io.*
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream
import java.util.zip.ZipOutputStream
import javax.swing.JButton
import javax.swing.filechooser.FileSystemView


@Serializable
data class JS(val surname:String, val name:String, val secondName:String, val age:Int)
fun main(){
    val kok=FileSystemView.getFileSystemView()
    val lol=File.listRoots()
    for(disk in lol){
        println("Название:$disk")
        println("Тип: ${kok.getSystemTypeDescription(disk)}")
        println("Объём диска:${disk.totalSpace}")
        println("Свободное пространство:${disk.freeSpace}")
        println("Метка:$disk")
    }
    /*val file=File("C:\\Для лабы","laba1_1.txt")
    file.createNewFile()
    println("Введите строку")
    val str=readLine().toString()
    file.writeText(str.toString())
    println(file.readText())
    file.delete()

    println("Введите свою фамилию")
    val sur= readLine().toString()
    println("Введите своё имя")
    val name= readLine().toString()
    println("Введите своё отчество")
    val second= readLine().toString()
    println("Введите свой возраст")
    val age=readLine().toString().toInt()
    val json = Json.encodeToString(JS(sur,name,second,age))
    val fileJs=File("C:\\Для лабы","laba1_2.txt")
    fileJs.writeText(json)
    println(fileJs.readText())
    fileJs.delete()*/
    val file3=File("C:\\untitled5","laba1_4.xml")
    val xml= readLine().toString()
    val e = XMLEncoder(
        BufferedOutputStream(
            FileOutputStream("laba1_4.xml")
        )
    )
    e.writeObject(JButton(xml))
    e.close()
    val d = XMLDecoder(
        BufferedInputStream(
            FileInputStream("laba1_4.xml")
        )
    )
    println(d.readObject().toString())
    d.close()
file3.delete()
    val file4=File("C:\\untitled5","notes.txt")
    file4.writeText("Что угодно")
    println()
    val filename = "C:\\untitled5\\notes.txt"
    try {
        ZipOutputStream(FileOutputStream("C:\\untitled5\\output.zip")).use { zout ->
            FileInputStream(filename).use { fis ->
                val entry1 = ZipEntry("notes.txt")
                zout.putNextEntry(entry1)
                // считываем содержимое файла в массив byte
                val buffer = ByteArray(fis.available())
                fis.read(buffer)
                // добавляем содержимое к архиву
                zout.write(buffer)
                // закрываем текущую запись для новой записи
                zout.closeEntry()
            }
        }
    } catch (ex: Exception) {
        println(ex.message)
    }
    try {
        ZipInputStream(FileInputStream("C:\\untitled5\\output.zip")).use { zin ->
            var entry: ZipEntry
            var name: String
            var size: Long
            while (zin.nextEntry.also { entry = it } != null) {
                name = entry.name // получим название файла
                size = entry.size // получим его размер в байтах
                System.out.printf("File name: %s \t File size: %d \n", name, size)

                // распаковка
                val fout = FileOutputStream("C:\\untitled5\\new$name")
                var c = zin.read()
                while (c != -1) {
                    fout.write(c)
                    c = zin.read()
                }
                fout.flush()
                zin.closeEntry()
                fout.close()
            }
        }
    } catch (ex: java.lang.Exception) {
        println(ex.message)
    }
    file4.delete()
    val file5=File("C:\\untitled5","output.zip")
    file5.delete()
}