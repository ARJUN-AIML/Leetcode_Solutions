int* validSequence(char* word1, char* word2, int* returnSize) {

    int n = strlen(word1);
    int m = strlen(word2);
    int* suffix = (int*)malloc((n + 1) * sizeof(int));
    suffix[n] = 0;
    int j = m - 1;
    for (int i = n - 1; i >= 0; i--) {

        if (j >= 0 && word1[i] == word2[j]) {
            j--;
        }

        suffix[i] = (m - 1) - j;
    }
    int* answer = (int*)malloc(m * sizeof(int));

    int answerIndex = 0;
    j = 0;
    int changed = 0;
    for (int i = 0; i < n && answerIndex < m; i++) {
        if (word1[i] == word2[j]) {

            answer[answerIndex] = i;

            answerIndex++;
            j++;
        }
        else if (!changed) {
            int remaining = m - (j + 1);
            if (suffix[i + 1] >= remaining) {

                answer[answerIndex] = i;

                answerIndex++;
                j++;

                changed = 1;
            }
        }
    }

    free(suffix);
    if (answerIndex != m) {

        free(answer);

        *returnSize = 0;

        return NULL;
    }

    *returnSize = m;

    return answer;
}