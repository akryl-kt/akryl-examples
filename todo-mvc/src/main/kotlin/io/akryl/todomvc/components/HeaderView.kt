package io.akryl.todomvc.components

import io.akryl.component
import io.akryl.dom.css.css
import io.akryl.dom.css.invoke
import io.akryl.dom.css.properties.*
import io.akryl.dom.html.h1
import io.akryl.dom.html.header
import io.akryl.dom.html.input
import io.akryl.todomvc.store.useStore
import io.akryl.useState
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.KeyboardEvent

val headerClass by css(
    position.absolute(),
    top(-155.px),
    width(100.pct),
    fontSize(100.px),
    fontWeight.w100(),
    textAlign.center(),
    color.rgba(175, 47, 47, 0.15)
)

val newTodo by css(
    position.relative(),
    margin.none(),
    width(100.pct),
    fontSize(24.px),
    fontFamily.inherit(),
    fontWeight.inherit(),
    lineHeight(1.4),
    color.inherit(),
    boxSizing.borderBox(),
    padding(16.px, 16.px, 16.px, 60.px),
    borderStyle.none(),
    backgroundColor.rgba(0, 0, 0, 0.003),
    boxShadow(0.px, -2.px, 1.px, 0.px, rgba(0, 0, 0, 0.03)),

    placeholder(
        fontStyle.italic(),
        fontWeight.w300(),
        color(0xE6E6E6)
    )
)

fun headerView() = component {
    val store = useStore()
    val (title, setTitle) = useState("")

    fun keyDown(event: KeyboardEvent) {
        if (event.keyCode==13 && title.isNotBlank()) {
            store.add(title)
            setTitle("")
        }
    }

    header(
        h1(className = headerClass, text = "todos"),
        input(
            className = newTodo,
            placeholder = "What's need to be done?",
            value = title,
            onChange = { setTitle((it.target as HTMLInputElement).value) },
            onKeyDown = { keyDown(it) }
        )
    )
}
