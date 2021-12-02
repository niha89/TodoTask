# TodoTask
[todo-task-api]
A Todo contains an arbitrary list of subtasks and is structured as follows:

---
{
    id [mandatory]
    name [mandatory]
    description
    tasks: [
        {
            id [mandatory]
            name [mandatory]
            description
        }
    ]
}
---

# Getting Started

These are the CRUD endpoints available.

·         GET /todos → Returns a list of all Todos

·         POST /todos → Expects a Todo (without id) and returns a Todo with id

·         GET /todos/{id} → Returns a Todo

·         PUT /todos/{id} → Overwrites an existing Todo

·         DELETE /todos/{id} → Deletes a Todo

