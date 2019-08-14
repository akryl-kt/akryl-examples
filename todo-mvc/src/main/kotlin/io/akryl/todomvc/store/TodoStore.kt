package io.akryl.todomvc.store

import io.akryl.rx.computed
import io.akryl.store.Store
import io.akryl.store.StoreContext

data class Task(
  var id: String,
  var title: String,
  var completed: Boolean
)

data class TaskFilter(
  val name: String,
  val url: String,
  val predicate: (Task) -> Boolean
)

val TodoContext = StoreContext.create<TodoStore>()

class TodoStore : Store() {
  companion object {
    fun use() = of(TodoContext)
  }

  var items = arrayListOf(
    Task(id = uuid4(), title = "Task 1", completed = false),
    Task(id = uuid4(), title = "Task 2", completed = true),
    Task(id = uuid4(), title = "Task 3", completed = false)
  )

  val filters = listOf(
    TaskFilter(
      name = "All",
      url = "/",
      predicate = { true }
    ),
    TaskFilter(
      name = "Active",
      url = "/active",
      predicate = { !it.completed }
    ),
    TaskFilter(
      name = "Completed",
      url = "/completed",
      predicate = { it.completed }
    )
  )

  var filter: TaskFilter = filters[0]

  val filtered by computed { items.filter(filter.predicate) }

  val allCompleted by computed { items.all { it.completed } }

  fun add(title: String) {
    items.add(Task(
      id = uuid4(),
      title = title,
      completed = false
    ))
    println(items)
  }

  fun toggle(id: String) {
    val task = items.first { it.id == id }
    task.completed = !task.completed
  }

  fun clearCompleted() {
    items.removeAll { it.completed }
  }

  fun delete(id: String) {
    items.removeAt(items.indexOfFirst { it.id == id })
  }

  fun toggleAll() {
    val value = !allCompleted
    items.forEach { it.completed = value }
  }

  fun rename(id: String, title: String) {
    val task = items.first { it.id == id }
    task.title = title
  }

  fun get(id: String) = items.firstOrNull { it.id == id }

  fun remove(id: String) {
    items.removeAll { it.id == id }
  }
}

@JsModule("uuid/v4")
external fun uuid4(): String
