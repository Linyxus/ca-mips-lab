package mips

import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._

class ALUDecoderSpec extends FreeSpec with ChiselScalatestTester {
  "ALU decoder should work properly" in {
    test(new ALUDecoder()) { c =>
      val cases = Map(
        ("b00".U, 0.U) -> "b010".U,
        ("b00".U, 1.U) -> "b010".U,
        ("b01".U, 1.U) -> "b110".U,
        ("b11".U, 0.U) -> "b110".U,
        ("b10".U, "b100000".U) -> "b010".U,
        ("b10".U, "b100010".U) -> "b110".U,
        ("b10".U, "b100100".U) -> "b000".U,
        ("b10".U, "b100101".U) -> "b001".U,
        ("b10".U, "b101010".U) -> "b111".U,
      )

      cases foreach { case ((aluOp, funct), aluCtl) =>
        c.io.aluOp.poke(aluOp)
        c.io.funct.poke(funct)
        c.io.aluCtl.expect(aluCtl)
      }
    }
  }
}
