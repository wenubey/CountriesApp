data class CountryDto(
    val name: Name,
    val capital: List<String>?,
    val population: Int,
    val currencies: List<Currency>,
    val subregion: String?,
    val languages: List<Language>
)

data class Name(
    val common: String,
    val official: String,
    val nativeName: List<NativeName>
)

data class NativeName(
    val code: CodeName,
)

data class CodeName(
    val official: String,
    val common: String,
)

data class Language(
    val code: String,
)

data class Currency(
    val name: CurrencyDetail
)

data class CurrencyDetail(
    val name: String,
    val symbol: String,
)
