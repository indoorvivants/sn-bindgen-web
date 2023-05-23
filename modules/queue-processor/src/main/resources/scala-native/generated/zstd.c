#include <string.h>
#include "zstd.h"

void __sn_wrap_zstd_ZSTD_cParam_getBounds(ZSTD_cParameter cParam, ZSTD_bounds *____return) {
  ZSTD_bounds ____ret = ZSTD_cParam_getBounds(cParam);
  memcpy(____return, &____ret, sizeof(ZSTD_bounds));
}


void __sn_wrap_zstd_ZSTD_dParam_getBounds(ZSTD_dParameter dParam, ZSTD_bounds *____return) {
  ZSTD_bounds ____ret = ZSTD_dParam_getBounds(dParam);
  memcpy(____return, &____ret, sizeof(ZSTD_bounds));
}


