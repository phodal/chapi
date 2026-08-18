/*
 * Adapted from OpenHarmony arkcompiler_ets_frontend StructTest1.ets.
 * Upstream commit: 862c79486f3d7cdff6e5605b7949a02ef3162de1.
 * Licensed under the Apache License, Version 2.0.
 */
struct CE {
  x: Int = 1;
}

function main(): void {
  let a: CE = new CE;
}
