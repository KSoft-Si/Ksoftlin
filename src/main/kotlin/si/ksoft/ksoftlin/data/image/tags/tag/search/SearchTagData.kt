package si.ksoft.ksoftlin.data.image.tags.tag.search

import si.ksoft.ksoftlin.data.image.tags.tag.Tag

data class SearchTagResponse(val models: List<Tag>, val tags: List<String>)