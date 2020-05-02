package io.akryl.todomvc.components

import io.akryl.component
import io.akryl.dom.css.css
import io.akryl.dom.css.properties.float
import io.akryl.dom.css.properties.textAlign
import io.akryl.dom.html.span
import io.akryl.todomvc.store.useStore

val todoCount by css(
    float.left(),
    textAlign.left()
)

fun todoCountView() = component {
    val store = useStore()
    val count = store.items.count { !it.completed }
    val str = if (count==1) "1 item left" else "$count items left"

    span(className = todoCount, text = str)
}
