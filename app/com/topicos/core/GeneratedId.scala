package com.topicos.core

case class GeneratedId(id: Long) {
  Asserts.argumentIsTrue(id > 0)
}
