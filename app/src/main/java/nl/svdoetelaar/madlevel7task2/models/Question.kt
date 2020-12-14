package nl.svdoetelaar.madlevel7task2.models

data class Question(
    val question: String,
    val answers: List<String>,
    val correctAnswer: String
)