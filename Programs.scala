import chisel3._
import chisel3.util._
import chisel3.iotesters
import chisel3.iotesters.PeekPokeTester
import java.util

object Programs{
  val program1 = Array(
    "b0101_0000_0000_0000_0000_0000_0000_0000".U(32.W), // LI R0, 0;
    "b0101_0110_0000_0000_0000_0000_0001_0100".U(32.W), // LI R6, 20;
    "b0101_0111_0000_0000_0000_0000_0001_0011".U(32.W), // LI R7, 19;
    "b0101_1000_0000_0000_0000_0000_1111_1111".U(32.W), // LI R8, 255;
    "b0101_0001_0000_0000_0000_0000_0000_0000".U(32.W),// LI R1, 0;
    "b0101_0010_0000_0000_0000_0000_0000_0000".U(32.W), // LI R2, 0;
    "b0101_0100_0000_0000_0000_0000_0000_0000".U(32.W), // LI R4, 0;
   "b0010_0100_0010_0110_0000_0000_0000_0000".U(32.W), // MULT R4, R2, R6;
    "b0001_0100_0100_0001_0000_0000_0000_0000".U(32.W), // ADD R4, R4, R1;
    "b0011_0101_0100_0000_0000_0001_1001_0000".U(32.W), // ADDI R5, R4, 400;
    "b1001_0000_0001_0000_0000_0000_0001_1111".U(32.W), // JEQ R1,R0, 31;// CHANGES HERE AS WELL
    "b1001_0000_0010_0000_0000_0000_0001_1111".U(32.W), // JEQ R2,R0, 31;// CHANGES HERE AS WELL
    "b1001_0000_0001_0111_0000_0000_0001_1111".U(32.W), // JEQ R1,R7, 31;// CHANGES HERE AS WELL
    "b1001_0000_0010_0111_0000_0000_0001_1111".U(32.W), // JEQ R2,R7, 31;// CHANGES HERE AS WELL
    "b0110_0011_0100_0000_0000_0000_0000_0000".U(32.W), // LD R3, R4;
    "b1001_0000_0011_0000_0000_0000_0001_1111".U(32.W), // JEQ R3,R0, 31;// CHANGES HERE AS WELL
    "b0100_0101_0100_0000_0000_0000_0000_0001".U(32.W), // SUBI R5, R4, 1;
    "b0110_0011_0101_0000_0000_0000_0000_0000".U(32.W), // LD R3, R5;
    "b1001_0000_0011_0000_0000_0000_0001_1111".U(32.W), // JEQ R3,R0, 31;  // CHANGES HERE AS WELL
    "b0011_0101_0100_0000_0000_0000_0000_0001".U(32.W), // ADDI R5, R4, 1;
    "b0110_0011_0101_0000_0000_0000_0000_0000".U(32.W), // LD R3, R5;
    "b1001_0000_0011_0000_0000_0000_0001_1111".U(32.W), // JEQ R3,R0, 31;// CHANGES HERE AS WELL
    "b0100_0101_0100_0000_0000_0000_0001_0100".U(32.W), // SUBI R5, R4, 20;
    "b0110_0011_0101_0000_0000_0000_0000_0000".U(32.W), // LD R3, R5;
    "b1001_0000_0011_0000_0000_0000_0001_1111".U(32.W), // JEQ R3,R0, 31;// CHANGES HERE AS WELL
    "b0011_0101_0100_0000_0000_0000_0001_0100".U(32.W), // ADDI R5, R4, 20;
    "b0110_0011_0101_0000_0000_0000_0000_0000".U(32.W), // LD R3, R5;
    "b1001_0000_0011_0000_0000_0000_0001_1111".U(32.W), // JEQ R3,R0, 31; // CHANGES HERE AS WELL
    "b0011_0101_0100_0000_0000_0001_1001_0000".U(32.W), // ADDI R5, R4, 400;
    "b0111_0000_0101_1000_0000_0000_0000_0000".U(32.W), // SD R5, R8; // CHANGES HERE AS WELL
    "b1000_0000_0000_0000_0000_0000_0010_0001".U(32.W), // JR 33;
    "b0011_0101_0100_0000_0000_0001_1001_0000".U(32.W), // ADDI R5, R4, 400;
    "b0111_0101_0000_0000_0000_0000_0000_0000".U(32.W), // SD R5, R0;
    "b0011_0010_0010_0000_0000_0000_0000_0001".U(32.W), // ADDI R2, R2, 1;
    "b1010_0000_0010_0110_0000_0000_0000_0110".U(32.W), // JLT R2, R6, 6; // CHANGES HERE PUT 0000 for writesel (before writesel would be R2, so R2 wasn't compared to R6)
    "b0011_0001_0001_0000_0000_0000_0000_0001".U(32.W), // ADDI R1, R1, 1;
    "b1010_0000_0001_0110_0000_0000_0000_0101".U(32.W), // JLT R1, R6, 5; // CHANGES HERE PUT 0000 for writesel
    "b1011_0000_0000_0000_0000_0000_0000_0000".U(32.W), // END;


  )

  val program2 = Array(
    "h00000000".U(32.W),
    "h00000000".U(32.W),
    "h00000000".U(32.W),
    "h00000000".U(32.W),
    "h00000000".U(32.W),
    "h00000000".U(32.W),
    "h00000000".U(32.W),
    "h00000000".U(32.W),
    "h00000000".U(32.W),
    "h00000000".U(32.W),
    "h00000000".U(32.W),
    "h00000000".U(32.W)
  )
}