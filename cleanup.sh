#!/bin/bash
# ============================================================
#  cleanup.sh — Script pembersih project Hidayah
#  Jalankan sekali dari dalam folder Hidayah:
#    bash cleanup.sh
# ============================================================

set -e
cd "$(dirname "$0")"

echo "======================================"
echo "  HIDAYAH Project Cleanup Script"
echo "======================================"
echo ""

# --- LANGKAH 1: Hapus folder bin/ ---
echo "[1/4] Menghapus folder bin/ (compiled output)..."
if [ -d "bin" ]; then
    rm -rf bin/
    echo "      ✓ bin/ berhasil dihapus"
else
    echo "      ✓ bin/ tidak ditemukan, dilewati"
fi

# --- LANGKAH 2: Rename file yang ada spasi di namanya ---
echo ""
echo "[2/4] Memperbaiki nama file yang mengandung spasi..."

rename_if_exists() {
    local old="$1"
    local new="$2"
    if [ -f "$old" ]; then
        mv "$old" "$new"
        echo "      ✓ $(basename "$old") → $(basename "$new")"
    fi
}

rename_if_exists "res/tiles/papan tulis_kanan.png"     "res/tiles/papan_tulis_kanan.png"
rename_if_exists "res/tiles/papan tulis_kiri.png"      "res/tiles/papan_tulis_kiri.png"
rename_if_exists "res/tiles/rak buku_1.png"            "res/tiles/rak_buku_1.png"
rename_if_exists "res/tiles/rak buku_atas_kanan.png"   "res/tiles/rak_buku_atas_kanan.png"
rename_if_exists "res/tiles/rak buku_atas_kiri.png"    "res/tiles/rak_buku_atas_kiri.png"
rename_if_exists "res/tiles/rak buku_bawah_kanan.png"  "res/tiles/rak_buku_bawah_kanan.png"
rename_if_exists "res/tiles/rak buku_bawah_kiri.png"   "res/tiles/rak_buku_bawah_kiri.png"
rename_if_exists "res/objects/kursi_1 (1).png"         "res/objects/kursi_1_alt.png"

# --- LANGKAH 3: Rename file sound yang bermasalah ---
echo ""
echo "[3/4] Memperbaiki nama file sound..."
SPOTIFYFILE='res/sound/spotifydown.com - WELCOME TO SAHARA BITCH.wav'
if [ -f "$SPOTIFYFILE" ]; then
    mv "$SPOTIFYFILE" "res/sound/welcome_theme.wav"
    echo "      ✓ File sound bermasalah berhasil direname → welcome_theme.wav"
else
    echo "      ✓ File sound sudah bersih"
fi

# --- LANGKAH 4: Hapus file map lama yang tidak dipakai ---
echo ""
echo "[4/4] Menghapus file map lama (worldV2, worldV3, map01)..."
echo "      PERHATIAN: Pastikan file-file ini memang sudah tidak dipakai!"
echo "      Tekan Enter untuk lanjut, atau Ctrl+C untuk batalkan langkah ini."
read -r

for f in "res/maps/worldV2.txt" "res/maps/worldV3.txt" "res/maps/map01.txt"; do
    if [ -f "$f" ]; then
        rm "$f"
        echo "      ✓ $f dihapus"
    fi
done

echo ""
echo "======================================"
echo "  ✅ Cleanup selesai!"
echo "======================================"
echo ""
echo "Langkah selanjutnya:"
echo "  1. Perbarui kode Java jika ada referensi ke nama file lama"
echo "     (papan tulis → papan_tulis, rak buku → rak_buku, dll)"
echo "  2. Jalankan: git init"
echo "  3. Jalankan: git add ."
echo "  4. Jalankan: git commit -m 'Initial commit'"
echo "  5. Push ke GitHub sesuai panduan README"
echo ""
