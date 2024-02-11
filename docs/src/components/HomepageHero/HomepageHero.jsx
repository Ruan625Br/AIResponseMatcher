import React from "react";
import styles from "./HomepageHero.module.css";

export default function HomepageHero() {
  return (
    <div className={styles.container}>
      <h1 className={styles.logo}>
        <span className={styles.title}>
          <h1>AIResponseMatcher</h1>
        </span>
      </h1>
      <h2 className={styles.tagline}>
        <p>
          <span className={styles.nonCode}>
            Streamline AI output processing with a single line of code:
          </span>
          <code className={styles.code}>output.processLines()</code>
          <span className={styles.nonCode}>
            , and watch your operations seamlessly come to life. It's that
            simple!
          </span>
        </p>
      </h2>
    </div>
  );
}
