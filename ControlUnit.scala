import chisel3._
import chisel3.util._

class ControlUnit extends Module {
  val io = IO(new Bundle {
    //Define the module interface here (inputs/outputs)
    val opCode = Input(UInt(4.W))

    val condJump = Output(Bool())
    val imJump = Output(Bool())
    val writeEnable = Output(Bool())
    val run = Output(Bool())
    val aluSrc = Output(Bool())
    val memWrite = Output(Bool())
    val memToReg = Output(Bool())
    val aluOp = Output(UInt(3.W))
    val stop = Output(Bool())
  })

  io.stop := false.B
  io.condJump := false.B
  io.imJump := false.B
  io.writeEnable := false.B
  io.run := false.B
  io.aluSrc := false.B
  io.memWrite := false.B
  io.memToReg := false.B
  io.aluOp := "b000".U

  //Implement this module here
  switch(io.opCode) {
    is("b0000".U) {} // Nothing?
    is("b0001".U) { // ADD
      io.writeEnable := true.B
      io.aluOp := "b001".U

      io.condJump := false.B
      io.imJump := false.B
      io.run := true.B
      io.aluSrc := false.B
      io.memWrite := false.B
      io.memToReg := false.B
    }
    is("b0010".U) { // MULT
      io.writeEnable := true.B
      io.aluOp := "b010".U

      io.condJump := false.B
      io.imJump := false.B
      io.run := true.B
      io.aluSrc := false.B
      io.memWrite := false.B
      io.memToReg := false.B
    }
    is("b0011".U) { // ADDI
      io.writeEnable := true.B
      io.aluSrc := true.B
      io.aluOp := "b001".U

      io.condJump := false.B
      io.imJump := false.B
      io.run := true.B
      io.memWrite := false.B
      io.memToReg := false.B
    }
    is("b0100".U) { // SUBI
      io.writeEnable := true.B
      io.aluSrc := true.B
      io.aluOp := "b100".U

      io.condJump := false.B
      io.imJump := false.B
      io.run := true.B
      io.memWrite := false.B
      io.memToReg := false.B
    }
    is("b0101".U) { // LI
      io.writeEnable := true.B
      io.aluSrc := true.B
      io.aluOp := "b101".U

      io.condJump := false.B
      io.imJump := false.B
      io.run := true.B
      io.memWrite := false.B
      io.memToReg := false.B
    }
    is("b0110".U) { // LD
      io.writeEnable := true.B
      io.aluSrc := false.B
      io.memWrite := true.B
      io.memToReg := true.B
      io.aluOp := "b101".U

      io.condJump := false.B
      io.imJump := false.B
      io.run := true.B
    }
    is("b0111".U) { // SD
      io.writeEnable := false.B
      io.aluSrc := false.B
      io.memWrite := true.B
      io.memToReg := false.B
      io.aluOp := "b101".U

      io.condJump := false.B
      io.imJump := false.B
      io.run := true.B
    }
    is("b1000".U) { // JR
      io.imJump := true.B

      io.condJump := false.B
      io.writeEnable := false.B
      io.run := true.B
      io.aluSrc := false.B
      io.memWrite := false.B
      io.memToReg := false.B
      io.aluOp := "b000".U
    }
    is("b1001".U) { // JEQ
      io.condJump := true.B
      io.aluOp := "b011".U
      io.aluSrc := true.B

      io.imJump := false.B
      io.writeEnable := false.B
      io.run := true.B
      io.memWrite := false.B
      io.memToReg := false.B
    }
    is("b1010".U) { // JLT
      io.condJump := true.B
      io.aluOp := "b000".U

      io.imJump := false.B
      io.writeEnable := false.B
      io.run := true.B
      io.aluSrc := false.B
      io.memWrite := false.B
      io.memToReg := false.B
    }
    is("b1011".U) { // END
      io.run := false.B
      io.stop := true.B

      io.condJump := false.B
      io.imJump := false.B
      io.writeEnable := false.B
      io.aluSrc := false.B
      io.memWrite := false.B
      io.memToReg := false.B
      io.aluOp := "b000".U
    }
    is("b1100".U) {}
    is("b1101".U) {}
    is("b1110".U) {}
    is("b1111".U) {}
  }
}