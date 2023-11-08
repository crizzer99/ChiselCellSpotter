import chisel3._
import chisel3.util._

class ALU extends Module {
  val io = IO(new Bundle {
    //Define the module interface here (inputs/outputs)
    val sel = Input(UInt(3.W))
    val regA = Input(UInt(32.W))
    val opB = Input(UInt(32.W))

    val result = Output(UInt(32.W))
    val zero = Output(Bool())
  })

  io.result := 0.U
  io.zero := false.B
  //Implement this module here
  switch (io.sel) {
    is("b000".U) {} // No action
    is("b001".U) {io.result := io.regA + io.opB} // Add
    is("b010".U) {io.result := io.regA * io.opB} // MULT
    is("b011".U) {io.zero := io.regA === io.opB} // JEQ
    is("b100".U) {io.result := io.regA - io.opB} // SUBI
    is("b101".U) {io.result := io.regA} // SD/LD
    is("b110".U) {io.zero := io.regA < io.opB} // JLT
    is("b111".U) {io.result := io.opB} // LI

  }
}