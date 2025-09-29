package domain.model

data class Recipe(
    val id: String,
    val name: String,
    val image: String,
    val mealType: List<String>
)
