package mips

import chisel3._
import util._

class MainDecoder extends Module {
  val io = IO {
    new Bundle {
      val opcode = Input(UInt(6.W))
      val regWrite = Output(Bool())
      val regDst = Output(Bool())
      val aluSrc = Output(Bool())
      val branch = Output(Bool())
      val memWrite = Output(Bool())
      val memToReg = Output(Bool())
      val aluOp = Output(UInt(2.W))
    }
  }

  io.regWrite := false.B
  io.regDst := false.B
  io.aluSrc := false.B
  io.branch := false.B
  io.memWrite := false.B
  io.memToReg := false.B
  io.aluOp := 0.U

  switch (io.opcode) {
    // R-type inst
    is("b000000".U) {
      io.regWrite := true.B
      io.regDst := true.B
      io.aluSrc := false.B
      io.branch := false.B
      io.memWrite := false.B
      io.memToReg := false.B
      io.aluOp := "b10".U
    }

    // lw
    is("b100011".U) {
      io.regWrite := true.B
      io.regDst := false.B
      io.aluSrc := true.B
      io.branch := false.B
      io.memWrite := false.B
      io.memToReg := false.B
      io.aluOp := "b00".U
    }

    // sw
    is("b101011".U) {
      io.regWrite := false.B
      io.aluSrc := true.B
      io.branch := false.B
      io.memWrite := true.B
      io.aluOp := "b00".U
    }

    // beq
    is("b000100".U) {
      io.regWrite := false.B
      io.aluSrc := false.B
      io.branch := true.B
      io.memWrite := false.B
      io.aluOp := "b01".U
    }
  }
}
