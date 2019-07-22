package io.akryl.todomvc

import io.akryl.*
import io.akryl.html.*
import io.akryl.rx.computed
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.KeyboardEvent

class TodoMvc : StatefulWidget() {
  override fun style() = css {
    ".root" {
      width = 550.px
      margin = "auto"
    }

    ".content" {
      background = "white"
      boxShadow = "0 2px 4px 0 rgba(0, 0, 0, 0.2), 0 25px 50px 0 rgba(0, 0, 0, 0.1)"
    }

    ".header" {
      display = "flex"
      alignItems = "center"
    }

    "h1" {
      fontSize = 100.px
      fontWeight = "100"
      textAlign = "center"
      color = "rgba(175, 47, 47, 0.15)"
    }

    "input" {
      width = "100%"
      padding = "16px 16px 16px 0"
      fontSize = 24.px
      border = "none"
      outline = "none"
    }

    "input::placeholder" {
      color = "#e6e6e6"
      fontWeight = "300"
      fontStyle = "italic"
    }

    ".footer" {
      color = "#777"
      display = "flex"
      alignItems = "center"
      padding = "10px 15px"
      borderTop = "1px solid lightgray"
    }

    ".filters" {
      display = "flex"
      flex = "1 1 100%"
      justifyContent = "center"

      "a" {
        color = "inherit"
        textDecoration = "none"
        border = "1px solid transparent"
        borderRadius = "3px"
        padding = "3px 7px"
        margin = "3px"
      }

      "a:hover" {
        borderColor = "rgba(175, 47, 47, 0.2)"
      }

      "a.selected" {
        borderColor = "rgba(175, 47, 47, 0.2)"
      }
    }

    ".active-text" {
      flex = "0 0 120px"
    }

    ".clear-button" {
      flex = "0 0 120px"
      background = "none"
      outline = "none"
      border = "none"
      color = "inherit"
      visibility = "hidden"
    }

    ".clear-button:hover" {
      textDecoration = "underline"
    }

    ".clear-button.show" {
      visibility = "visible"
    }

    ".toggle-all" {
      flex = "0 0 28px"
      margin = "0 16px"
      borderRadius = "50%"
      border = "1px solid lightgray"
      height = 28.px
    }

    ".toggle-all.completed:after" {
      content = "'\\2713'"
      color = "green"
      width = 26.px
      display = "block"
      textAlign = "center"
      fontSize = 18.px
      marginTop = "-1px"
    }
  }

  override fun createState(context: BuildContext): State<*> = TodoMvcState(context)
}

private class TodoMvcState(context: BuildContext) : State<TodoMvc>(context) {
  private val store = TodoStore.of(context)
  private val showFooter by computed { store.tasks.isNotEmpty() }
  private val showClear by computed { store.tasks.any { it.completed } }

  private val activeText by computed {
    val count = store.tasks.count { !it.completed }
    if (count != 1) {
      "$count items left"
    } else {
      "$count item left"
    }
  }

  override fun build(context: BuildContext) = Div(clazz = "root", children = listOf(
    H1(child = Text("todos")),
    Div(clazz = "content", children = listOf(
      Div(clazz = "header", children = listOf(
        Div(classes = classMap("toggle-all" to true, "completed" to store.allCompleted), onClick = { store.toggleAll() }),
        Input(placeholder = "What needs to be done?", onKeyDown = this::addTodo.enter)
      )),
      Div(clazz = "body", children = store.filtered.map { TaskTile(it) }),
      *If(showFooter) {
        Div(clazz = "footer", children = listOf(
          Div(clazz = "active-text", child = Text(activeText)),
          Div(clazz = "filters", children = store.filters.map { filter ->
            A(
              classes = classMap("filter" to true, "selected" to (filter == store.filter)),
              href = filter.url,
              child = Text(filter.name),
              onClick = { store.filter = filter }.prevent
            )
          }),
          Button(
            classes = classMap("clear-button" to true, "show" to showClear),
            onClick = { store.clearCompleted() },
            child = Text("Clear completed")
          )
        ))
      }
    ))
  ))

  private fun addTodo(event: KeyboardEvent) {
    val target = event.target as HTMLInputElement
    val title = target.value
    store.add(title)
    target.value = ""
  }
}
