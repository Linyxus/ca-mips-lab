package simple_cpu

import chisel3._
import util._
import chisel3.experimental._

class ROM extends BlackBox {
  val io = IO {
    new Bundle {
      val A = Input(UInt(32.W))
      val RD = Output(UInt(32.W))
    }
  }
}

