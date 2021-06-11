package mips

import chisel3._
import util._

class ALU(aluWidth: Int) extends Module {
  val io = IO {
    new Bundle {
      val a = Input(UInt(aluWidth.W))
      val b = Input(UInt(aluWidth.W))
      val ctl = Input(UInt(3.W))
      val y = Output(UInt(aluWidth.W))
    }
  }

  switch (io.ctl) {
    is("b000".U) {
      io.y := io.a & io.b
    }
    is("b001".U) {
      io.y := io.a | io.b
    }
    is("b010".U) {
      io.y := io.a + io.b
    }
    is("b011".U) {
      io.y := 0.U
    }
    is("b100".U) {
      io.y := io.a & (~io.b)
    }
    is("b101".U) {
      io.y := io.a | (~io.b)
    }
    is("b110".U) {
      io.y := io.a - io.b
    }
    is("b111".U) {
      io.y := Mux(io.a < io.b, 1.U, 0.U)
    }
  }
}

