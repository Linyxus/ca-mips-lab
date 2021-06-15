package mips

import chisel3._
import util._

class ALUDecoder extends Module {
  val io = IO {
    new Bundle {
      val funct = Input(UInt(6.W))
      val aluOp = Input(UInt(2.W))
      val aluCtl = Output(UInt(3.W))
    }
  }

  when (io.aluOp === "b00".U) {
    io.aluCtl := "b010".U
  }

  when (io.aluOp(0) === 1.U) {
    io.aluCtl := "b110".U
  }

  when (io.aluOp(1) === 1.U) {
    switch (io.funct) {
      is ("b100000".U) {
        io.aluCtl := "b010".U
      }
      is ("b100010".U) {
        io.aluCtl := "b110".U
      }
      is ("b100100".U) {
        io.aluCtl := "b000".U
      }
      is ("b100101".U) {
        io.aluCtl := "b001".U
      }
      is ("b101010".U) {
        io.aluCtl := "b111".U
      }
    }
  }
}
