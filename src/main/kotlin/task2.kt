const val alphabet = "АБВГДЕЖЗИКЛМНОПРСТУФХЦЧШЩЬЫЪЭЮЯ"
const val amountChars = alphabet.length
fun main() {
    println("Программа для шифровки и расшифровки сообщений шифром Порты.")

    print("Введите исходное сообщение: ")
    val originalMessage = readln().uppercase()

    print("Введите вспомогательный символ (в случае, если число символов нечетное): ")
    val dopSymbol = readln().uppercase().firstOrNull() ?: 'Я'
    val normalizedMessage = normalizeMessage(originalMessage, dopSymbol)

    println("Выберите тип таблицы (введите 1 или 2):")
    println("1. Типовая таблица")
    println("2. Случайная таблица")

    val tableType = readln().toIntOrNull() ?: 1

    // Создание шифровальной таблицы
    val cipherTable = generateCipherTable(tableType)

    val formattedTable = formatCipherTable(cipherTable)
    println("Шифровальная таблица:")
    println(formattedTable)

    val encryptedMessage = encryptMessage(normalizedMessage, cipherTable)
    println("Исходное сообщение: $originalMessage")
    println("Зашифрованное сообщение: $encryptedMessage")
}

fun normalizeMessage(message: String, dopSymbol: Char?): String {
    return if (message.length % 2 == 1) {
        message + dopSymbol
    } else {
        message
    }
}

// Функция, которая генерирует шифровальную таблицу
fun generateCipherTable(type: Int): Array<IntArray> {
    val table = Array(amountChars) { IntArray(amountChars) }

    if (type == 1) {
        // Типовая таблица
        for (i in 0..<amountChars) {
            for (j in 0..<amountChars) {
                table[i][j] = i * amountChars + j + 1
            }
        }
    } else if (type == 2) {
        // Случайная таблица
        val numbers = (1..amountChars * amountChars).toList().shuffled()
        var k = 0
        for (i in 0..<amountChars) {
            for (j in 0..<amountChars) {
                table[i][j] = numbers[k]
                k++
            }
        }
    }

    return table
}

fun encryptMessage(message: String, table: Array<IntArray>): String {
    val encryptedNumbers = mutableListOf<String>()

    for (i in message.indices step 2) {
        val firstChar = message[i]
        val secondChar = message[i + 1]
        val rowIndex = getIndex(firstChar)
        val columnIndex = getIndex(secondChar)

        if (rowIndex != -1 && columnIndex != -1) {
            encryptedNumbers.add(String.format("%03d", table[rowIndex][columnIndex]))
        } else {
            // Обработка ошибки, если символ не найден
            println("Ошибка: символ не найден в таблице.")
            return "\"Ошибка: текст должен быть без пробелов\""
        }
    }

    return encryptedNumbers.joinToString(" ")
}

fun getIndex(char: Char): Int {
    var validatedChar = char
    if (char == 'Ё') {
        validatedChar = 'E'
    } else if (char == 'Й') {
        validatedChar = 'И'
    }
    return alphabet.indexOf(validatedChar)
}

fun formatCipherTable(table: Array<IntArray>): String {
    val formattedTable = StringBuilder()

    for (i in 0..<amountChars) {
        for (j in 0..<amountChars) {
            formattedTable.append(String.format("%03d", table[i][j]))
            formattedTable.append(" ")
        }
        formattedTable.append("\n")
    }

    return formattedTable.toString()
}