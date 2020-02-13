package io.akryl.todomvc.components

import io.akryl.component
import io.akryl.dom.css.classMap
import io.akryl.dom.css.css
import io.akryl.dom.css.invoke
import io.akryl.dom.css.properties.*
import io.akryl.dom.html.A
import io.akryl.dom.html.Li
import io.akryl.dom.html.Span
import io.akryl.dom.html.Ul
import io.akryl.todomvc.store.useStore
import org.w3c.dom.events.Event
import react.ReactElement

val filters by css(
    margin(0.px),
    padding(0.px),
    listStyleType.none(),
    position.absolute(),
    right(0.px),
    left(0.px)
)

val filterLink by css(
    color.inherit(),
    margin(3.px),
    padding(3.px, 7.px),
    textDecoration.none(),
    border.solid(1.px, Color.transparent),
    borderRadius(3.px),

    hover(
        borderColor.rgba(175, 47, 47, 0.1)
    )
)

val selected by css(
    borderColor.rgba(175, 47, 47, 0.2)
)

val filterView by css(
    display.inline()
)

fun filtersView() = component {
    val store = useStore()

    val children = ArrayList<ReactElement<*>>()
    for ((index, filter) in store.filters.withIndex()) {
        val linkClass = classMap(
            filterLink to true,
            selected to (store.filter === filter)
        )

        val onClick = { e: Event ->
            store.setFilter(index)
            e.preventDefault()
        }

        children.add(
            Li(className = filterView, onClick = onClick, children = listOf(
                A(className = linkClass, href = filter.url, text = filter.name)
            ))
        )
        if (index < store.filters.size - 1) {
            children.add(Span())
        }
    }

    Ul(className = filters, children = children)
}
