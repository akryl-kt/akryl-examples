package io.akryl.todomvc.components

import io.akryl.component
import io.akryl.dom.css.css
import io.akryl.dom.css.invoke
import io.akryl.dom.css.properties.*
import io.akryl.dom.html.Button
import io.akryl.todomvc.store.useStore

val clearCompleted by css(
    float.right(),
    position.relative(),
    lineHeight(20.px),
    textDecoration.none(),
    cursor.pointer(),

    hover(
        textDecoration.underline()
    )
)

fun clearCompletedView() = component {
    val store = useStore()

    Button(
        className = clearCompleted,
        onClick = { store.clearCompleted() },
        text = "Clear completed"
    )
}
