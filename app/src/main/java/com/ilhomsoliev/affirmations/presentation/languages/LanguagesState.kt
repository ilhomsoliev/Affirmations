package com.ilhomsoliev.affirmations.presentation.languages

import com.ilhomsoliev.affirmations.core.Helper
import com.ilhomsoliev.affirmations.core.model.Language

data class LanguagesState(
    val languages: List<Language> = Helper.getLanguages(),
    val selectedLanguage: Language? = null,
)