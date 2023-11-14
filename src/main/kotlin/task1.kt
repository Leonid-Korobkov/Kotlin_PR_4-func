import kotlin.random.Random

fun main() {
    println("Игра Камень-Ножницы-Бумага!")

    while (true) {
        println("Выберите действие:")
        println("1. Камень\n" +
                "2. Ножницы\n" +
                "3. Бумага\n" +
                "0. Выход")

        val playerChoice = readln().toIntOrNull()

        when (playerChoice) {
            1, 2, 3 -> {
                val computerChoice = Random.nextInt(1, 4)
                printResult(playerChoice, computerChoice)
            }
            0 -> {
                println("Игра завершена.")
                break
            }
            else -> println("Некорректный выбор. Пожалуйста, введите 1, 2, 3 или 0.")
        }
    }
}

fun printResult(playerChoice: Int, computerChoice: Int) {
    println("Вы выбрали: ${getStringChoice(playerChoice)}")
    println("Компьютер выбрал: ${getStringChoice(computerChoice)}")

    when {
        playerChoice == computerChoice -> println("Ничья! Переиграйте.")
        playerChoice == 3 && computerChoice == 1 ||
                playerChoice == 1 && computerChoice == 2 ||
                playerChoice == 2 && computerChoice == 3 -> println("Поздравляем! Вы победили!")
        else -> println("Компьютер победил. Попробуйте еще раз.")
    }
}

fun getStringChoice(choice: Int): String {
    return when (choice) {
        1 -> "Камень"
        2 -> "Ножницы"
        3 -> "Бумага"
        else -> "Неизвестный выбор"
    }
}
