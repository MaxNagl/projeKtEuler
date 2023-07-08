package problems

import java.io.File
import java.io.OutputStream
import java.io.PrintStream
import kotlin.math.roundToInt
import kotlin.system.measureNanoTime

fun main() {
    val out = System.out
    for (i in 10001..19999) {
        val pClass = Problem::class.java
        runCatching {
            val clazz = pClass.classLoader.loadClass(pClass.name + i.toString().substring(1, 5))
            val instance = clazz.constructors.first().newInstance() as Problem
            System.setOut(out)
            runCatching { instance.run(true) }.onFailure {
                System.setOut(out)
                out.println("\u001B[31mError!\u001B[0m")
            }
        }
    }
}

abstract class Problem(
    val result: Any? = null,
    val meassureRepeat: Int = 100
) {
    abstract fun calc(): Any

    @Suppress("UNREACHABLE_CODE")
    fun run(runAll: Boolean = false) {
        val out = System.out
        if (runAll) {
            print(javaClass.simpleName.replaceFirst("0", " 0") + ": ")
            System.setOut(PrintStream(OutputStream.nullOutputStream()))
        }
        val calc = calc()
        println(calc)
        if (result != null && calc != result && calc.toString() != result.toString()) {
            if (runAll) {
                out.println("\u001B[31mWrong!\u001B[0m")
                return
            }
            System.err.println(result)
            throw error("Wrong!")
        }
        val meassureRepeat = if (runAll) (1) else meassureRepeat
        System.setOut(PrintStream(OutputStream.nullOutputStream()))
        measureStage("Init", meassureRepeat / 2, out)
        val time = measureStage("Measuring", meassureRepeat, out)
        val ms = (time / meassureRepeat / 1000f).roundToInt() / 1000f
        if (runAll)
            out.println("$calc in $ms ms.")
        else
            out.println("In $ms ms.")
        System.setOut(out)
    }

    private fun measureStage(
        stage: String,
        repeat: Int = 0,
        out: PrintStream,
    ): Long {
        var time = 0L
        var statusOut = ""
        repeat(repeat) { count ->
            time += measureNanoTime { calc() }
            if (repeat != 1) {
                out.print("\b".repeat(statusOut.length))
                val progress = count / repeat.toFloat()
                val percent = (progress * 100).roundToInt()
                statusOut =
                    "$stage: " + "+".repeat((progress * 20).roundToInt()) + "-".repeat(((1 - progress) * 20).roundToInt()) + " ${count + 1} of $repeat ($percent%)"
                out.print(statusOut)
            }
        }
        out.print("\b".repeat(statusOut.length))
        return time
    }


    private var dataCache: Any? = null

    private fun dataFile(file: String?) =
        File("src/main/kotlin/data/" + (file ?: javaClass.simpleName) + ".txt")

    @Suppress("UNCHECKED_CAST")
    fun <T> getDataLines(file: String? = null, transform: (String) -> T): List<T> {
        dataCache?.let { return dataCache as List<T> }
        dataCache = dataFile(file).readText().split("\n").map { transform(it.trim()) }
        return dataCache as List<T>
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> getDataCsvLines(file: String? = null, transform: (String) -> T): List<List<T>> {
        dataCache?.let { return dataCache as List<List<T>> }
        dataCache = dataFile(file).readText().split("\n")
            .map { l -> l.split(",").map { transform(it.trim()) } }
        return dataCache as List<List<T>>
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> getData(file: String? = null, transform: (String) -> T): T {
        dataCache?.let { return dataCache as T }
        dataCache = transform(dataFile(file).readText()) as Any
        return dataCache as T
    }

}

fun runProblem() {
    val problem = Thread.currentThread().stackTrace.last().className.dropLast(2)
    val clazz = Problem::class.java.classLoader.loadClass(problem)
    val instance = clazz.constructors.first().newInstance() as Problem
    instance.run()
}
