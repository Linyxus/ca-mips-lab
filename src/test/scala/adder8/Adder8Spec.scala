package adder8

import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._

class Adder8Spec extends FreeSpec with ChiselScalatestTester {

  "adder8 should calculate sum of two integer" in {
    test(new Adder8()) { c =>
      c.io.enable.poke(true.B)

      c.io.a.poke(0.U)
      c.io.b.poke(0.U)
      c.io.y.expect(0.U)

      c.io.a.poke(1.U)
      c.io.b.poke(2.U)
      c.io.y.expect(3.U)
    }
  }

}
