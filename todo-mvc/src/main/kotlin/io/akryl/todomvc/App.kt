package io.akryl.todomvc

import io.akryl.BuildContext
import io.akryl.StatelessWidget
import io.akryl.hotMount
import io.akryl.store.StoreProvider
import kotlin.browser.document

class TodoMvcApp : StatelessWidget() {
  override fun build(context: BuildContext) = StoreProvider(
    store = { TodoStore() },
    child = TodoMvc()
  )
}

fun main() {
  val app = document.getElementById("app")!!
  hotMount(app, TodoMvcApp())
}
