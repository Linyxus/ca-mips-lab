package simple_cpu

import chisel3._
import util._
import chisel3.experimental._

class SignExtend extends BlackBox {
  val io = IO {
    new Bundle {
      val SignImm16 = Input(UInt(16.W))
      val SignImm32 = Output(UInt(32.W))
    }
  }
}
