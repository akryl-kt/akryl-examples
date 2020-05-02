package io.akryl.todomvc.components

import io.akryl.component
import io.akryl.dom.css.*
import io.akryl.dom.css.properties.*
import io.akryl.dom.html.*
import io.akryl.todomvc.store.useStore
import io.akryl.useState
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event

private val destroy by css(
    display.none(),
    position.absolute(),
    top(0.px),
    right(10.px),
    bottom(0.px),
    width(40.px),
    height(40.px),
    margin(Linear.auto, 0.px),
    fontSize(30.px),
    color(0xCC9A9A),
    marginBottom(11.px),
    transition(color, 0.2.s, Timing.easeOut),

    after(
        content("×")
    ),

    hover(
        color(0xaf5b5e)
    )
)

private val root by css(
    position.relative(),
    fontSize(24.px),
    borderBottom.solid(1.px, Color(0xE6E6E6)),
    listStyleType.none(),

    hover(
        destroy(
            display.block()
        )
    )
)

private val toggle by css(
    height(40.px),
    background.none(),
    opacity(0),
    textAlign.center(),
    width(40.px),
    position.absolute(),
    top(0.px),
    bottom(0.px),
    margin(Linear.auto, 0.px),
    borderStyle.none(),

    StyleProperty("-webkit-appearance", "none"),
    StyleProperty("appearance", "none")
)

private val label by css(
    backgroundImage.url("data:image/svg+xml;utf8,%3Csvg%20xmlns%3D%22http%3A//www.w3.org/2000/svg%22%20width%3D%2240%22%20height%3D%2240%22%20viewBox%3D%22-10%20-18%20100%20135%22%3E%3Ccircle%20cx%3D%2250%22%20cy%3D%2250%22%20r%3D%2250%22%20fill%3D%22none%22%20stroke%3D%22%23ededed%22%20stroke-width%3D%223%22/%3E%3C/svg%3E"),
    backgroundRepeat.noRepeat(),
    backgroundPosition(Horizontal.left, Vertical.center),
    wordBreak.breakAll(),
    padding(15.px, 15.px, 15.px, 60.px),
    display.block(),
    lineHeight(1.2),
    transition(color, 0.4.s),
    userSelect("none")
)

private val edit by css(
    position.relative(),
    width(506.px),
    margin(0.px, 0.px, 0.px, 43.px),
    fontSize(24.px),
    fontFamily.inherit(),
    fontWeight.inherit(),
    lineHeight(1.4.em),
    color.inherit(),
    padding(12.px, 16.px),
    border.solid(1.px, Color(0x909090)),
    boxShadow(true, 0.px, -1.px, 5.px, 0.px, rgba(0, 0, 0, 0.2)),
    boxSizing.borderBox()
)

private val completed by css(
    tag("label")(
        backgroundImage.url("data:image/svg+xml;utf8,%3Csvg%20xmlns%3D%22http%3A//www.w3.org/2000/svg%22%20width%3D%2240%22%20height%3D%2240%22%20viewBox%3D%22-10%20-18%20100%20135%22%3E%3Ccircle%20cx%3D%2250%22%20cy%3D%2250%22%20r%3D%2250%22%20fill%3D%22none%22%20stroke%3D%22%23bddad5%22%20stroke-width%3D%223%22/%3E%3Cpath%20fill%3D%22%235dc2af%22%20d%3D%22M72%2025L42%2071%2027%2056l-4%204%2020%2020%2034-52z%22/%3E%3C/svg%3E"),
        textDecoration.lineThrough(),
        color(0xD9D9D9)
    )
)

fun todoView(id: String) = component {
    val store = useStore()
    val (editingTitle, setEditingTitle) = useState<String?>(null)
    val todo = store.find(id) ?: return@component null

    val rootClass = classMap(
        root to true,
        completed to todo.completed
    )

    val editBlur = { _: Event ->
        if (editingTitle!=null && editingTitle.isNotBlank()) {
            store.rename(id, editingTitle)
        }
        setEditingTitle(null)
    }

    val titleChanged = { e: Event ->
        setEditingTitle((e.target as HTMLInputElement).value)
    }

    li(className = rootClass, children = listOf(
        *IfNotNull(editingTitle) { title ->
            input(
                className = edit,
                autoFocus = true,
                value = title,
                onChange = titleChanged,
                onBlur = editBlur
            )
        } Else {
            div(
                input(
                    className = toggle,
                    type = "checkbox",
                    checked = todo.completed,
                    onChange = { store.toggle(id) }
                ),
                label(
                    className = label,
                    text = todo.title,
                    onDoubleClick = { setEditingTitle(todo.title) }
                ),
                button(
                    className = destroy,
                    onClick = { store.remove(id) }
                )
            )
        }
    ))
}
