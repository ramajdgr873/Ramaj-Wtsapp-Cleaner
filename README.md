# Ramaj Wtsapp Cleaner
Native Android duplicate cleaner designed for WhatsApp media.

## Detection
- Full SHA-256 confirmation for exact duplicates.
- Real image perceptual hash (32x32 DCT pHash) for recompressed/resized/renamed images.
- Native Android `MediaMetadataRetriever` three-frame visual fingerprints for similar/re-encoded videos.
- SQLite hash cache, resume-by-cache, on-device processing.

## Safety
- Exact duplicates can be auto-moved.
- Visual/Possible matches are review-first.
- Type-routed SD destination: `what's app duplicate files/<TYPE>/...`.
- Move history supports restore.

## UI
- Dark premium dashboard.
- Circular progress, live stage, exact/visual/possible counters, recovered-space card and mini chart.
- Duplicate review with confidence scores.
- History & restore.
- Strict/Balanced/Aggressive visual sensitivity.

## Build
The repository contains a GitHub Actions workflow. Run **Build Ramaj Wtsapp Cleaner APK**, then download the `Ramaj-Wtsapp-Cleaner-APK` artifact.
