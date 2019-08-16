package io.akryl.todomvc.store

import io.akryl.rx.computed
import io.akryl.store.Store
import io.akryl.store.StoreContext
import io.akryl.todomvc.entities.Task
import io.akryl.todomvc.repositories.TaskRepository

data class TaskFilter(
  val name: String,
  val url: String,
  val predicate: (Task) -> Boolean
)

val TodoContext = StoreContext.create<TodoStore>()

class TodoStore(private val repository: TaskRepository) : Store() {
  companion object {
    fun use() = of(TodoContext)
  }

  val items = ArrayList(repository.getAll())

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
    repository.saveAll(items)
  }

  fun toggle(id: String) {
    val task = items.first { it.id == id }
    task.completed = !task.completed
    repository.saveAll(items)
  }

  fun clearCompleted() {
    items.removeAll { it.completed }
    repository.saveAll(items)
  }

  fun toggleAll() {
    val value = !allCompleted
    items.forEach { it.completed = value }
    repository.saveAll(items)
  }

  fun rename(id: String, title: String) {
    val task = items.first { it.id == id }
    task.title = title
    repository.saveAll(items)
  }

  fun get(id: String) = items.firstOrNull { it.id == id }

  fun remove(id: String) {
    items.removeAll { it.id == id }
    repository.saveAll(items)
  }
}

@JsModule("uuid/v4")
external fun uuid4(): String
