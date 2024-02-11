import React, { useState } from "react";
import styles from "./CallToAction.module.css";

export default function CallToAction() {
  const [isCopied, setIsCopied] = useState(false);

  function clickToCopy() {
    setIsCopied(true);
    navigator.clipboard.writeText("npm install easy-dates");
    setTimeout(() => {
      setIsCopied(false);
    }, 5000);
  }

  return (
    <div className={styles.container}>
      <h3 className={styles.title}>Sound good?</h3>
      <p className={styles.paragraphTwo}>
        Check the <a href="/docs/intro">docs</a> for detailed instructions.
      </p>
    </div>
  );
}
