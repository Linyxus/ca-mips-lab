package adder8

import chisel3._

class Adder8 extends Module {
  val io = IO{
    new Bundle {
      val enable = Input(Bool())
      val a = Input(UInt(8.W))
      val b = Input(UInt(8.W))
      val y = Output(UInt(8.W))
    }
  }

  when (io.enable) {
    io.y := io.a + io.b
  } .otherwise {
    io.y := 0.U
  }
}
