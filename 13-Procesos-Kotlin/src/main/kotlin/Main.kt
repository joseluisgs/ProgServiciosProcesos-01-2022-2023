fun main() {
    println("Hola Procesos")

    // Proceso de ejecución de un comando ls -ls
    // val p = Runtime.getRuntime().exec("ls -ls")
    val ls = ProcessBuilder("ls", "-ls", ".").start()
    // Asi lo leemos todo con la Colecciones
    val lsOut = ls.inputStream.bufferedReader()
        .lineSequence()
        .filter { it.contains(".kts") || it.contains(".bat") }
        .joinToString("\n")
    println(lsOut)

    ls.waitFor()
    var exitValue = ls.exitValue()
    println("Valor de salida proceso ls: $exitValue")

    // Proceso de ejecución de un comando cat
    // Tomamos la primera linea
    val ficheroCat = lsOut.lines().first().split(" ").last()
    println(ficheroCat)

    // Proceso de ejecución de un comando cat
    val cat = ProcessBuilder("cat", ficheroCat).start()
    cat.waitFor()

    // La salida del cat se la pasamos a la entrada del grep
    val grep = ProcessBuilder("grep", "kotlinOptions.jvmTarget").start()

    // Leemos el cat
    val catOut = cat.inputStream.bufferedReader().readText()
    // Lo pasamos al grep
    grep.outputStream.bufferedWriter().use { it.write(catOut) }
    // leer el grep
    val grepOut = grep.inputStream.bufferedReader().readText()

    exitValue = cat.exitValue()
    println("Valor de salida proceso cat: $exitValue")
    grep.waitFor()
    exitValue = grep.exitValue()
    println("Valor de salida proceso cat: $exitValue")

    // El contenido a buscar era:
    // kotlinOptions.jvmTarget = "1.8"
    println("El contenido a buscar era: $grepOut")
    // Has compilado tu programa para una version
    // de Java inferior a la 1.8
    println(
        "Has compilado para la version JVM: ${
            grepOut.trim()
                .split(" ")
                .last().replace("\"", "")
        }"
    )
}