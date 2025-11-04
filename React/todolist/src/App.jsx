import React, { useEffect, useState } from "react";
import TodoForm from "./components/TodoForm";
import TodoList from "./components/TodoList";
import "./index.css";

/* Load todos from localStorage safely */
const loadTodos = () => {
  try {
    const raw = localStorage.getItem("todos");
    return raw ? JSON.parse(raw) : [];
  } catch {
    return [];
  }
};

export default function App() {
  const [todos, setTodos] = useState(loadTodos); // main list
  const [editTodo, setEditTodo] = useState(null); // item under edit

  // save to localStorage whenever todos change -> persistence
  useEffect(() => {
    localStorage.setItem("todos", JSON.stringify(todos));
  }, [todos]);

  const addTodo = (text) => {
    if (!text || !text.trim()) return;
    const newTodo = {
      id: Date.now().toString() + Math.random().toString(36).slice(2),
      text: text.trim(),
    };
    setTodos((prev) => [newTodo, ...prev]);
  };

  const updateTodo = (id, newText) => {
    setTodos((prev) => prev.map(t => t.id === id ? { ...t, text: newText } : t));
    setEditTodo(null);
  };

  const deleteTodo = (id) => {
    setTodos((prev) => prev.filter(t => t.id !== id));
    if (editTodo && editTodo.id === id) setEditTodo(null);
  };

  const startEdit = (todo) => setEditTodo(todo);
  const cancelEdit = () => setEditTodo(null);

  return (
    <div className="container">
      <h1>To-Do List</h1>

      <TodoForm
        onAdd={addTodo}
        editTodo={editTodo}
        onUpdate={updateTodo}
        onCancelEdit={cancelEdit}
      />

      <TodoList todos={todos} onEdit={startEdit} onDelete={deleteTodo} />
    </div>
  );
}