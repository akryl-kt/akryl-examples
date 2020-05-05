package io.akryl.todomvc.components

import io.akryl.component
import io.akryl.dom.css.classMap
import io.akryl.dom.css.css
import io.akryl.dom.css.invoke
import io.akryl.dom.css.properties.*
import io.akryl.dom.html.li
import io.akryl.dom.html.span
import io.akryl.dom.html.ul
import io.akryl.router.LocationDescriptor
import io.akryl.router.link
import io.akryl.todomvc.store.useStore
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

        children.add(
            li(className = filterView, children = listOf(
                link(className = linkClass, to = LocationDescriptor(pathname = filter.url), text = filter.name)
            ))
        )
        if (index < store.filters.size - 1) {
            children.add(span())
        }
    }

    ul(className = filters, children = children)
}
