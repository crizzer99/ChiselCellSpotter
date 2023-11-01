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

  io.zero := 1.U
  io.result := 0.U
  //Implement this module here
  switch (io.sel) {
    is("b000".U) {io.zero := io.regA < io.opB} // aluOp for jump if less than
    is("b001".U) {io.result := io.regA + io.opB}
    is("b010".U) {io.result := io.regA * io.opB}
    is("b011".U) {io.zero := io.regA === io.opB} // aluOp for jump if equal
    is("b100".U) {io.result := io.regA - io.opB}
    is("b101".U) {io.result := io.opB}
    is("b110".U) {io.result := io.opB} // same as b110
    is("b111".U) {io.result := io.regA}

  }
}