package simple_cpu

import chisel3._
import util._
import chisel3.experimental._

class RAM extends BlackBox {
  val io = IO {
    new Bundle {
      val clk = Input(Clock())
      val WE = Input(Bool())
      val A = Input(UInt(32.W))
      val WD = Input(UInt(32.W))
      val RD = Output(UInt(32.W))
    }
  }
}

