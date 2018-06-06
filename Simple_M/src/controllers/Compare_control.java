	

	

	/* find which part of lines are different in each line. */

	private void inLineDiff(String l, String l2, JTextPane tP, JTextPane tP2) throws Exception {

		String[] part = l.split("\\s");

		String[] part2 = l2.split("\\s");

		int j = 0;

		for (j = 0; j < part.length - 1; j++) {

			part[j] = part[j].toString() + " ";

		}

		for (j = 0; j < part2.length; j++) {

			part2[j] = part2[j].toString() + " ";

		}

		int min_array = MIN(part.length, part2.length);

		if (part.length > part2.length) {

			for (int n = 0; n < min_array; n++) {

				if (LCS(part[n], part2[n]) != MAX(part[n].length(), part2[n].length())) {

					insert_word(tP, Color.red, Color.orange, part[n]);

					insert_word(tP2, Color.red, Color.orange, part2[n]);

				} else {

					insert_word(tP, Color.red, Color.yellow, part[n]);

					insert_word(tP2, Color.red, Color.yellow, part2[n]);

				}

			}

			for (int n = min_array; n < part.length; n++) {

				insert_word(tP, Color.red, Color.orange, part[n]);

			}

		} else if (part.length < part2.length) {

			for (int n = 0; n < min_array; n++) {

				if (LCS(part[n], part2[n]) != MAX(part[n].length(), part2[n].length())) {

					insert_word(tP, Color.red, Color.orange, part[n]);

					insert_word(tP2, Color.red, Color.orange, part2[n]);
