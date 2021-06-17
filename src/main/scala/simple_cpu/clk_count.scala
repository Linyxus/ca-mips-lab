package simple_cpu

import chisel3._
import util._
import chisel3.experimental._

class clk_count extends BlackBox {
  val io = IO {
    new Bundle {
      val clk = Input(Clock())
      val reset = Input(Reset())
      val count = Output(UInt(3.W))
    }
  }
}
