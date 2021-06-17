package simple_cpu

import chisel3._
import util._
import chisel3.experimental._

class Register extends BlackBox {
  val io = IO {
    new Bundle {
      val clk = Input(Clock())
      val WE3 = Input(Bool())
      val A1 = Input(UInt(5.W))
      val A2 = Input(UInt(5.W))
      val A3 = Input(UInt(5.W))
      val WD3 = Input(UInt(32.W))
      val RD1 = Output(UInt(32.W))
      val RD2 = Output(UInt(32.W))
    }
  }
}

