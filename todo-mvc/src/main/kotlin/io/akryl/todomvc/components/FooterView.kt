package io.akryl.todomvc.components

import io.akryl.Component
import io.akryl.css.Color
import io.akryl.css.css
import io.akryl.css.px
import io.akryl.css.rgba
import io.akryl.html.Footer
import io.akryl.react.ReactNode
import io.akryl.todomvc.Theme

private val footer by css {
  color(0x707070)
  padding(10.px, 15.px)
  height(20.px)
  textAlign.center()
  borderTop.solid(1.px, Theme.background)

  before {
    content("")
    position.absolute()
    right(0.px)
    bottom(0.px)
    left(0.px)
    height(50.px)
    overflow.hidden()

    boxShadow(0.px, 1.px, 1.px, 0.px, rgba(0, 0, 0, 0.2))
    boxShadow(0.px, 8.px, 0.px, -3.px, Color(0xF6F6F6))
    boxShadow(0.px, 9.px, 1.px, -3.px, rgba(0, 0, 0, 0.2))
    boxShadow(0.px, 16.px, 0.px, -6.px, Color(0xF6F6F6))
    boxShadow(0.px, 17.px, 2.px, -6.px, rgba(0, 0, 0, 0.2))
  }
}

class FooterView : Component() {
  override fun render(): ReactNode {
    return Footer(clazz = footer, children = listOf(
      TodoCountView(),
      FiltersView(),
      ClearCompletedView()
    ))
  }
}
